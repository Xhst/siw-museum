package it.uniroma3.siw.controller;

import it.uniroma3.siw.dto.CollectionDto;
import it.uniroma3.siw.controller.validator.CollectionValidator;
import it.uniroma3.siw.model.WorksCollection;
import it.uniroma3.siw.service.WorksCollectionService;

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
public class CollectionController {

    private final WorksCollectionService collectionService;
    private final CollectionValidator collectionValidator;

    @GetMapping(value = "/collection/{id}")
    public String getCollection(@PathVariable("id") Long id, Model model) {
        model.addAttribute("classActiveCollection",  "active");
        model.addAttribute("collection", this.collectionService.getById(id));
        return "collection.html";
    }

    @GetMapping(value = { "/collections", "/collection" })
    public String getCollections(Model model) {
        model.addAttribute("classActiveCollection",  "active");
        model.addAttribute("collections", this.collectionService.getAll());
        return "collections.html";
    }

    @PostMapping(value = "/admin/addCollection")
    public String newCollection(@ModelAttribute("collection") CollectionDto collectionDto,
                                Model model, BindingResult bindingResult) {
        this.collectionValidator.validate(collectionDto, bindingResult);

        if (!bindingResult.hasErrors()) {
            WorksCollection collection = this.collectionService.save(collectionDto);

            return "redirect:/collection/" + collection.getId();
        }

        return "redirect:/admin/home";
    }
}
