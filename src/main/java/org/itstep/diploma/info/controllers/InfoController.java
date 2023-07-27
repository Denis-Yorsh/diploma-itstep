package org.itstep.diploma.info.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/massage/{name}/{email}/{massage}")
    public String massage(@PathVariable("name") String name,
                          @PathVariable("email") String email,
                          @PathVariable("massage") String massage) {
        System.out.println(name);
        System.out.println(email);
        System.out.println(massage);
        return "redirect:/";
    }
}
