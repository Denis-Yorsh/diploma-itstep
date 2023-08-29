package org.itstep.diploma.user.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.user.dto.UserChangeRegistrationDataDto;
import org.itstep.diploma.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userPage")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping("/homePage")
	public String homePageUser() {
		return "user/userHomePage";
	}

	@PostMapping("/settings")
	public String settings() {
		return "user/userSettings";
	}

	@PostMapping("/changeRegistrationData")
	@ResponseBody
	public String changeRegistrationData(UserChangeRegistrationDataDto userChangeRegistrationDataDto,
										 Authentication authentication) {
		return userService.changeRegistrationData(userChangeRegistrationDataDto, authentication);
	}
}
