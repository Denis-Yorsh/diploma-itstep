package org.itstep.diploma.controllers.home.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/learnMore/")
public class LearnMoreController {

    @GetMapping("/individualTravelPage")
    public String individualTravel() {
        return "site/individualTravelPage";
    }

    @GetMapping("/italyPage")
    public String italyPage() {
        return "site/italyPage";
    }

    @GetMapping("/adventurePage")
    public String adventurePage() {
        return "site/adventurePage";
    }

    @GetMapping("/beachToursPage")
    public String beachToursPage() {
        return "site/beachToursPage";
    }
}