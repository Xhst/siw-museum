package it.uniroma3.siw.controller;

import it.uniroma3.siw.controller.dto.WorkDto;
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

    @PostMapping(value = "/admin/addWork")
    public String newWork(@ModelAttribute("work") WorkDto workDto,
                          Model model, BindingResult bindingResult) {
        this.workValidator.validate(workDto, bindingResult);

        if (!bindingResult.hasErrors()) {
            Work work = this.workService.save(workDto);

            return "redirect:/work/"+ work.getId();
        }

        return "redirect:/admin/home";
    }
}
