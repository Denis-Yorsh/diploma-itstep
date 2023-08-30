package org.itstep.diploma.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class AddDeleteRoleDto {
	@NotBlank(message = "username must be no blank")
	private String username;
	@NotBlank(message = "role must be no blank")
	private String role;
}
