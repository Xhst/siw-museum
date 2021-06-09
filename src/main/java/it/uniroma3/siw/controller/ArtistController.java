package it.uniroma3.siw.controller;

import it.uniroma3.siw.controller.validator.ArtistValidator;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.service.ArtistService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class ArtistController {

    private final ArtistValidator artistValidator;
    private final ArtistService artistService;

    @GetMapping(value = "/artist/{id}")
    public String getArtist(@PathVariable("id") Long id, Model model) {
        model.addAttribute("classActiveArtist",  "active");
        model.addAttribute("artist", this.artistService.getById(id));
        return "artist.html";
    }

    @GetMapping(value = { "/artists", "/artist" })
    public String getArtists(Model model) {
        model.addAttribute("classActiveArtist",  "active");
        model.addAttribute("artists", this.artistService.getAll());
        return "artists.html";
    }

    @PostMapping(value = "/admin/addArtist")
    public String addArtist(@ModelAttribute("artist") Artist artist,
                            BindingResult bindingResult,
                            Model model) {
        this.artistValidator.validate(artist, bindingResult);

        if (!bindingResult.hasErrors()) {
            artist = this.artistService.save(artist);

            return "redirect:/artist/" + artist.getId();
        }

        return "redirect:/admin/home";
    }
}
