package org.itstep.diploma.controllers.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/learnMore/")
public class LearnMoreController {

	@PostMapping("/individualTravelPage")
	public String individualTravel() {
		return "site/individualTravelPage";
	}

	@PostMapping("/italyPage")
	public String italyPage() {
		return "site/italyPage";
	}

	@PostMapping("/adventurePage")
	public String adventurePage() {
		return "site/adventurePage";
	}

	@PostMapping("/beachToursPage")
	public String beachToursPage() {
		return "site/beachToursPage";
	}
}