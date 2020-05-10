package com.common.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class demoController {
    @GetMapping("/htmlhome")
    public String mainPage(Model model) {

        model.addAttribute("message", "Hello Spring MVC 5!");
        return "htmlhome";
    }
}
