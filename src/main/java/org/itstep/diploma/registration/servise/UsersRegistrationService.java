package org.itstep.diploma.registration.servise;

import org.itstep.diploma.registration.dto.UsersRegistrationDto;

import java.util.Map;

public interface UsersRegistrationService {
	void createUser(UsersRegistrationDto usersRegistrationDto);

	Map<String, Boolean> checkUserNameAndEmail(String username, String email);
}
