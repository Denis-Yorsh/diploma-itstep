package org.itstep.diploma.user.service;

import org.itstep.diploma.user.dto.UserChangeRegistrationDataDto;
import org.springframework.security.core.Authentication;

public interface UserService {

	String changeRegistrationData(UserChangeRegistrationDataDto userChangeRegistrationDataDto,
								  Authentication authentication);
}
