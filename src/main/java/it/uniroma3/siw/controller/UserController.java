package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.oauth2.GoogleOAuth2User;
import it.uniroma3.siw.service.CredentialsService;

import lombok.AllArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class UserController {

    private final CredentialsService credentialsService;

    @GetMapping(value = { "/user", "/user/home" })
    public String home(Model model) {
        model.addAttribute("user", this.getCurrentUser());
        return "user/home";
    }

    @GetMapping(value = { "/admin", "/admin/home" })
    public String adminHome(Model model) {
        model.addAttribute("user", this.getCurrentUser());
        return "admin/home";
    }

    private User getCurrentUser() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;

        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else if (obj instanceof GoogleOAuth2User) {
            username = ((GoogleOAuth2User) obj).getEmail();
        }

        Credentials credentials = this.credentialsService.getCredentials(username);

        return credentials.getUser();
    }
}
