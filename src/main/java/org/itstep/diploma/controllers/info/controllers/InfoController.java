package org.itstep.diploma.controllers.info.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info/")
public class InfoController {

    @GetMapping("/aboutus")
    public String aboutUs() {
        return "site/aboutUs";
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "site/contacts";
    }
}
