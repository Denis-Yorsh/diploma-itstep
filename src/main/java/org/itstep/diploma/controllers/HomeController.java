package org.itstep.diploma.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Authentication authentication, Model model) {
        model.addAttribute("authenticationIsNull", authentication == null);
        return "site/homePage";
    }
}
