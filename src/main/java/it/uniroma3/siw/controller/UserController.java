package it.uniroma3.siw.controller;

import it.uniroma3.siw.dto.CollectionDto;
import it.uniroma3.siw.dto.WorkDto;
import it.uniroma3.siw.model.Artist;
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
        model.addAttribute("username", this.getCurrentUsername());
        model.addAttribute("user", this.getCurrentUser());
        return "user/home";
    }

    @GetMapping(value = { "/admin", "/admin/home" })
    public String adminHome(Model model) {
        model.addAttribute("user", this.getCurrentUser());

        model.addAttribute("artist", new Artist());
        model.addAttribute("work", new WorkDto());
        model.addAttribute("collection", new CollectionDto());

        return "admin/home";
    }

    private User getCurrentUser() {
        String username = this.getCurrentUsername();

        Credentials credentials = this.credentialsService.getByUsername(username);

        return credentials.getUser();
    }

    private String getCurrentUsername() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;

        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else if (obj instanceof GoogleOAuth2User) {
            username = ((GoogleOAuth2User) obj).getEmail();
        }

        return username;
    }
}
