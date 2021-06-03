package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.WorksCollectionService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class CollectionController {

    private final WorksCollectionService worksCollectionService;

    @GetMapping(value = "/collection/{id}")
    public String getCollection(@PathVariable("id") Long id, Model model) {
        model.addAttribute("classActiveCollection",  "active");
        model.addAttribute("collection", this.worksCollectionService.getCollectionById(id));
        return "collection.html";
    }

    @GetMapping(value = { "/collections", "/collection" })
    public String getCollections(Model model) {
        model.addAttribute("classActiveCollection",  "active");
        model.addAttribute("collections", this.worksCollectionService.getAll());
        return "collections.html";
    }
}
