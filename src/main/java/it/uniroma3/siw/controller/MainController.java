package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping(value = {"/", "/index", "/home"})
    public String index(Model model) {
        model.addAttribute("classActiveHome",  "active");
        return "index";
    }

    @GetMapping(value = "/info")
    public String info(Model model) {
        model.addAttribute("classActiveInfo",  "active");
        return "info";
    }
}
