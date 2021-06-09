package it.uniroma3.siw.configuration;

import it.uniroma3.siw.oauth2.GoogleOAuth2User;
import it.uniroma3.siw.oauth2.GoogleOAuth2UserService;
import it.uniroma3.siw.service.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

import static it.uniroma3.siw.model.Credentials.ADMIN_ROLE;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * The datasource is automatically injected into the AuthConfiguration (using its getters and setters)
     * and it is used to access the DB to get the Credentials to perform authentication and authorization
     */
    @Autowired
    DataSource datasource;

    @Lazy
    @Autowired
    private CredentialsService credentialsService;

    /**
     * This method provides the SQL queries to get username and password.
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                //use the autowired datasource to access the saved credentials
                .dataSource(this.datasource)
                //retrieve username and role
                .authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?")
                //retrieve username, password and a boolean flag specifying whether the user is enabled or not (always enabled in our case)
                .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
    }

    /**
     * This method defines a "passwordEncoder" Bean.
     * The passwordEncoder Bean is used to encrypt and decrypt the Credentials passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private GoogleOAuth2UserService oauthUserService;

    /**
     * This method provides the whole authentication and authorization configuration to use.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/user/**").authenticated()
            .antMatchers(HttpMethod.POST, "/user/**").authenticated()
            .antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
            .antMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
            .anyRequest().permitAll()

            .and().formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/default")

            .and().oauth2Login()
            .loginPage("/login")
            .userInfoEndpoint().userService(oauthUserService)
                .and().successHandler((request, response, authentication) -> {

                    GoogleOAuth2User oauthUser = (GoogleOAuth2User) authentication.getPrincipal();

                    credentialsService.processOAuthPostLogin(
                            oauthUser.getEmail(),
                            oauthUser.getGivenName(),
                            oauthUser.getFamilyName()
                    );

                    response.sendRedirect("/default");
                })

            .and().logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .invalidateHttpSession(true)
            .clearAuthentication(true).permitAll();
    }

}