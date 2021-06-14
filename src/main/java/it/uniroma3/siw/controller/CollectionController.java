package it.uniroma3.siw.controller;

import it.uniroma3.siw.dto.CollectionDto;
import it.uniroma3.siw.controller.validator.CollectionValidator;
import it.uniroma3.siw.model.Collection;
import it.uniroma3.siw.service.CollectionService;

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

    private final CollectionService collectionService;

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

    @GetMapping(value = "/admin/collection/{id}")
    public String getAdminCollection(@PathVariable("id") Long id, Model model) {
        model.addAttribute("collection", this.collectionService.getById(id));
        return "admin/collection/collection.html";
    }

    @GetMapping(value = { "/admin/collections", "/admin/collection" })
    public String getAdminCollections(Model model) {
        model.addAttribute("collections", this.collectionService.getAll());
        return "admin/collection/collections.html";
    }

    @GetMapping(value = "/admin/collection/add")
    public String addAdminCollection(Model model) {
        model.addAttribute("collection", new CollectionDto());
        return "admin/collection/addCollection.html";
    }

    @PostMapping(value = "/admin/collection/add")
    public String newCollection(@ModelAttribute("collection") CollectionDto collectionDto,
                                Model model, BindingResult bindingResult) {
        this.collectionValidator.validate(collectionDto, bindingResult);

        if (!bindingResult.hasErrors()) {
            Collection collection = this.collectionService.saveFromDto(collectionDto);

            return "redirect:/collection/" + collection.getId();
        }

        return "admin/collection/add";
    }


    @PostMapping(value = "/admin/collection/{id}/delete")
    public String deleteCollection(@PathVariable("id") Long id, Model model) {
        this.collectionService.deleteById(id);

        return "redirect:/admin/collections";
    }

}
