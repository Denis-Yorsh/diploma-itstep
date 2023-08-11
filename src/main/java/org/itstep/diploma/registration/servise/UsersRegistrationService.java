package org.itstep.diploma.registration.servise;

import org.itstep.diploma.configs.security.entity.Roles;
import org.itstep.diploma.configs.security.entity.Users;
import org.itstep.diploma.registration.dto.UsersRegistrationDto;
import org.itstep.diploma.registration.entity.UsersRegistration;

import java.util.Map;

public interface UsersRegistrationService {
	void createUser(Users users,
					Roles roles,
					UsersRegistration usersRegistration,
					UsersRegistrationDto usersRegistrationDto);

	Map<String, Boolean> checkUserNameAndEmail(String username, String email);
}
