package org.itstep.diploma.admin.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.admin.dto.AddDeleteRoleDto;
import org.itstep.diploma.admin.service.AddDeleteRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/homeRole")
public class AddDeleteRoleController {
	private final AddDeleteRoleService addDeleteRoleService;

	@PostMapping("/")
	public String homeRole() {
		return "admin/addDeleteRole";
	}

	@ResponseBody
	@PostMapping("/addRole")
	public String addRole(@Validated AddDeleteRoleDto addDeleteRoleDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
		}
		return addDeleteRoleDto.getRole().equals("noChoice")
				? "role no choice"
				: addDeleteRoleService.addRole(addDeleteRoleDto);
	}

	@ResponseBody
	@DeleteMapping("/deleteRole")
	public String deleteRole(@Validated AddDeleteRoleDto addDeleteRoleDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
		}
		return addDeleteRoleDto.getRole().equals("noChoice")
				? "role no choice"
				: addDeleteRoleService.deleteRole(addDeleteRoleDto);
	}
}
