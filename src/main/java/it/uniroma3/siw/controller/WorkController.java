package it.uniroma3.siw.controller;

import it.uniroma3.siw.controller.validator.WorkValidator;
import it.uniroma3.siw.model.Work;
import it.uniroma3.siw.service.WorkService;

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
public class WorkController {

    private final WorkService workService;
    private final WorkValidator workValidator;

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

    @PostMapping(value = "/admin/newWork")
    public String newOpera(@ModelAttribute("work") Work work,
                           Model model, BindingResult bindingResult) {
        this.workValidator.validate(work, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.workService.save(work);
        }
        return "admin/home.html";
    }
}
