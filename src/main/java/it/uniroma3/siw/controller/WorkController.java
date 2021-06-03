package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.WorkService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class WorkController {

    private final WorkService workService;

    @GetMapping(value = "/work/{id}")
    public String getWork(@PathVariable("id") Long id, Model model) {
        model.addAttribute("work", this.workService.getWorkById(id));
        return "work.html";
    }

    @GetMapping(value = { "/works", "/work" })
    public String getWorks(Model model) {
        model.addAttribute("works", this.workService.getAll());
        return "works.html";
    }
}
