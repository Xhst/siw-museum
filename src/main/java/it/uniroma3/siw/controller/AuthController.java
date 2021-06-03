package it.uniroma3.siw.controller;

import it.uniroma3.siw.controller.validator.CredentialsValidator;
import it.uniroma3.siw.controller.validator.UserValidator;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;

import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class AuthController {

    private final CredentialsService credentialsService;

    private final UserValidator userValidator;

    private final CredentialsValidator credentialsValidator;


    @GetMapping(value = "/register")
    public String showRegisterForm (Model model) {
        if (this.isLoggedIn()) {
            return "redirect:default";
        }

        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "register";
    }

    @GetMapping(value = "/login")
    public String showLoginForm (Model model, @RequestParam(required = false) boolean register) {
        if (this.isLoggedIn()) {
            return "redirect:default";
        }

        if (register) {
            model.addAttribute("registerSuccess", true);
        }

        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout(Model model) {
        return "index";
    }

    @GetMapping(value = "/default")
    public void defaultAfterLogin(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        if(role.contains(Credentials.ADMIN_ROLE)) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin/home"));
            return;
        }

        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user/home"));
    }

    @PostMapping(value = "/register")
    public String registerUser(@ModelAttribute("user") User user,
                               BindingResult userBindingResult,
                               @ModelAttribute("credentials") Credentials credentials,
                               BindingResult credentialsBindingResult,
                               Model model) {

        this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);

        if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {

            credentials.setUser(user);
            credentialsService.save(credentials);

            return "redirect:/login?register=true";
        }

        return "register";
    }

    private boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null ||
                AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}