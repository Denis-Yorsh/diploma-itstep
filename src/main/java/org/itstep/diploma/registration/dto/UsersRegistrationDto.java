package org.itstep.diploma.registration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public final class UsersRegistrationDto {
	@Length(min = 3, max = 20, message = "length must be from 3 to 20")
	@NotBlank(message = "field must be no blank")
	private String firstName;
	@Length(min = 3, max = 20, message = "length must be from 3 to 20")
	@NotBlank(message = "field must be no blank")
	private String lastName;
	@Past(message = "birthday must be in past time")
	private LocalDate dayBirthday;
	@Email(message = "email is invalid")
	@NotBlank(message = "field must be no blank")
	private String email;
	@Length(min = 3, max = 20, message = "length must be from 3 to 20")
	@NotBlank(message = "field must be no blank")
	private String username;
	@Length(min = 3, max = 20, message = "length must be from 3 to 20")
	@NotBlank(message = "field must be no blank")
	private String password;
	@Length(min = 3, max = 20, message = "length must be from 3 to 20")
	@NotBlank(message = "field must be no blank")
	private String repeatPassword;
}