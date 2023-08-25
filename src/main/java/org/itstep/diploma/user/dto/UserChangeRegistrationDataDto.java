package org.itstep.diploma.user.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public final class UserChangeRegistrationDataDto {
	private String firstName;
	private String lastName;
	private LocalDate dayBirthday;
	private String email;
	private String username;
	private String password;
	private String repeatPassword;
}
