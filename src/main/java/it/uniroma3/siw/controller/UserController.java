package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
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
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = this.credentialsService.getCredentials(userDetails.getUsername());

        return credentials.getUser();
    }
}
