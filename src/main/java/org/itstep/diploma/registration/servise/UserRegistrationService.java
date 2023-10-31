package org.itstep.diploma.registration.servise;

import org.itstep.diploma.configs.security.entity.Role;
import org.itstep.diploma.configs.security.entity.User;
import org.itstep.diploma.registration.dto.UserRegistrationDto;
import org.itstep.diploma.registration.entity.UserRegistration;

import java.util.Map;

public interface UserRegistrationService {
	void createUser(UserRegistrationDto userRegistrationDto,
					User user,
					Role role,
					UserRegistration userRegistration);

	Map<String, Boolean> checkUserNameAndEmail(String username, String email);
}
