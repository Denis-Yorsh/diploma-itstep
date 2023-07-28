package org.itstep.diploma.registration.validation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
public final class UsersRegistrationDto {
	@NotBlank
	@Length(min = 3)
	private String firstName;
	@NotBlank
	@Length(min = 3)
	private String lastName;
	@NotBlank
	@Past
	private Date dayBirthday;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Length(min = 3)
	private String username;
	@NotBlank
	@Length(min = 3)
	private String password;
	@NotBlank
	@Length(min = 3)
	private String repeatPassword;
}