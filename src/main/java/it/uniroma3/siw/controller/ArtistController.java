package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.ArtistService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping(value = "/artist/{id}")
    public String getArtist(@PathVariable("id") Long id, Model model) {
        model.addAttribute("classActiveArtist",  "active");
        model.addAttribute("artist", this.artistService.getArtistById(id));
        return "artist.html";
    }

    @GetMapping(value = { "/artists", "/artist" })
    public String getArtists(Model model) {
        model.addAttribute("classActiveArtist",  "active");
        model.addAttribute("artists", this.artistService.getAll());
        return "artists.html";
    }
}
