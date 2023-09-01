package org.itstep.diploma.registration.servise;

import org.itstep.diploma.registration.dto.UserRegistrationDto;

import java.util.Map;

public interface UserRegistrationService {
	void createUser(UserRegistrationDto userRegistrationDto);

	Map<String, Boolean> checkUserNameAndEmail(String username, String email);
}
