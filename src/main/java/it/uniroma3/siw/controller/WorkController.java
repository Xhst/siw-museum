package it.uniroma3.siw.controller;

import it.uniroma3.siw.dto.WorkDto;
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
        model.addAttribute("work", this.workService.getById(id));
        return "work.html";
    }

    @GetMapping(value = { "/works", "/work" })
    public String getWorks(Model model) {
        model.addAttribute("works", this.workService.getAll());
        return "works.html";
    }

    @GetMapping(value = "/admin/work/{id}")
    public String getAdminWork(@PathVariable("id") Long id, Model model) {
        model.addAttribute("work", this.workService.getById(id));
        return "admin/work/work.html";
    }

    @GetMapping(value = { "/admin/works", "/admin/work" })
    public String getAdminWorks(Model model) {
        model.addAttribute("works", this.workService.getAll());
        return "admin/work/works.html";
    }

    @GetMapping(value = "/admin/work/add")
    public String addAdminWorks(Model model) {
        model.addAttribute("work", new WorkDto());
        return "admin/work/addWork.html";
    }

    @PostMapping(value = "/admin/work/add")
    public String addWork(@ModelAttribute("work") WorkDto workDto,
                          Model model, BindingResult bindingResult) {
        this.workValidator.validate(workDto, bindingResult);

        if (!bindingResult.hasErrors()) {
            Work work = this.workService.saveFromDto(workDto);

            return "redirect:/admin/work/"+ work.getId();
        }

        return "admin/work/add";
    }

    @PostMapping(value = "/admin/work/{id}/delete")
    public String deleteWork(@PathVariable("id") Long id, Model model) {
        this.workService.deleteById(id);

        return "redirect:/admin/works";
    }
}
