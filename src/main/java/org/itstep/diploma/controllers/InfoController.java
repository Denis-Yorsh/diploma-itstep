package org.itstep.diploma.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info/")
public class InfoController {

    @GetMapping("/aboutus")
    public String aboutUs(Authentication authentication, Model model) {
        model.addAttribute("authenticationIsNull", authentication == null);
        return "site/aboutUs";
    }

    @GetMapping("/contacts")
    public String contacts(Authentication authentication, Model model) {
        model.addAttribute("authenticationIsNull", authentication == null);
        return "site/contacts";
    }
}
