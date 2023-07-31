package org.itstep.diploma.registration.servise;

import org.itstep.diploma.configs.security.entity.Users;
import org.itstep.diploma.registration.validation.dto.UsersRegistrationDto;

public interface UsersRegistrationService {
	Users createUser(UsersRegistrationDto usersRegistrationDto);
}
