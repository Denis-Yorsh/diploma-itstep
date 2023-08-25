package org.itstep.diploma.user.service;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.configs.security.entity.Users;
import org.itstep.diploma.configs.security.repository.UserRepository;
import org.itstep.diploma.user.dto.UserChangeRegistrationDataDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public String changeRegistrationData(UserChangeRegistrationDataDto userChangeRegistrationDataDto,
										 Authentication authentication) {
		if (!userChangeRegistrationDataDto.getPassword().isBlank() || !userChangeRegistrationDataDto.getRepeatPassword().isBlank()) {
			if (!userChangeRegistrationDataDto.getPassword().equals(userChangeRegistrationDataDto.getRepeatPassword())) {
				return "password mismatch";
			}
		}
		if (!userChangeRegistrationDataDto.getUsername().isBlank()) {
			Optional<Users> optionalUsers = userRepository.findByUsername(userChangeRegistrationDataDto.getUsername());
			if (optionalUsers.isPresent()) {
				return "username %s already exists".formatted(userChangeRegistrationDataDto.getUsername());
			}
		}
		Optional<Users> optionalUsersByUsername = userRepository.findByUsername(authentication.getName());
		Users user = null;
		if (optionalUsersByUsername.isPresent()) {
			user = optionalUsersByUsername.get();
		}
		if (user != null) {
			user.setUsername(userChangeRegistrationDataDto.getUsername().isBlank() ?
					user.getUsername() : userChangeRegistrationDataDto.getUsername());
			user.setPassword(userChangeRegistrationDataDto.getPassword().isBlank() ?
					user.getPassword() : passwordEncoder.encode(userChangeRegistrationDataDto.getPassword()));
			user.getUsersRegistration().setFirstName(userChangeRegistrationDataDto.getFirstName().isBlank() ?
					user.getUsersRegistration().getFirstName() : userChangeRegistrationDataDto.getFirstName());
			user.getUsersRegistration().setLastName(userChangeRegistrationDataDto.getLastName().isBlank() ?
					user.getUsersRegistration().getLastName() : userChangeRegistrationDataDto.getLastName());
			user.getUsersRegistration().setEmail(userChangeRegistrationDataDto.getEmail().isBlank() ?
					user.getUsersRegistration().getEmail() : userChangeRegistrationDataDto.getEmail());
			user.getUsersRegistration().setDayBirthday(userChangeRegistrationDataDto.getDayBirthday() == null ?
					user.getUsersRegistration().getDayBirthday() : userChangeRegistrationDataDto.getDayBirthday());
		}
		return "OK";
	}
}
