package org.itstep.diploma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogOutController {

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}