package org.itstep.diploma.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {
	@GetMapping("/adminHome")
	public String adminHomePage() {
		return "admin/adminHomePage";
	}
}
