package org.itstep.diploma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog/")
public class BlogController {

    @GetMapping("/homePage")
    public String homePage() {
        return "blog/blogHomePage";
    }
}
