package org.itstep.diploma.user.service;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.configs.security.entity.User;
import org.itstep.diploma.configs.security.repository.UserRepository;
import org.itstep.diploma.registration.entity.UserRegistration;
import org.itstep.diploma.registration.repository.UserRegistrationRepository;
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
	private final UserRegistrationRepository userRegistrationRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public String changeRegistrationData(UserChangeRegistrationDataDto userChangeRegistrationDataDto,
										 Authentication authentication) {
		Optional<User> optionalUsersByAuthentication = userRepository.findByUsername(authentication.getName());
		if (optionalUsersByAuthentication.isEmpty()) {
			return "YOU MUST RESTART YOUR ACCOUNT PLEASE";
		}
		if (!userChangeRegistrationDataDto.getUsername().isBlank()) {
			Optional<User> optionalUsers = userRepository.findByUsername(userChangeRegistrationDataDto.getUsername());
			if (optionalUsers.isPresent()) {
				return "username %s already exists".formatted(userChangeRegistrationDataDto.getUsername());
			}
		}
		if (!userChangeRegistrationDataDto.getEmail().isBlank()) {
			Optional<UserRegistration> optionalUsersRegistration = userRegistrationRepository.findByEmail(userChangeRegistrationDataDto.getEmail());
			if (optionalUsersRegistration.isPresent()) {
				return "email %s already exist".formatted(userChangeRegistrationDataDto.getEmail());
			}
		}
		Optional<User> optionalUsersByUsername = userRepository.findByUsername(authentication.getName());
		User user = null;
		if (optionalUsersByUsername.isPresent()) {
			user = optionalUsersByUsername.get();
		}
		if (user != null) {
			user.setUsername(userChangeRegistrationDataDto.getUsername().isBlank() ?
					user.getUsername() : userChangeRegistrationDataDto.getUsername());
			user.setPassword(userChangeRegistrationDataDto.getPassword().isBlank() ?
					user.getPassword() : passwordEncoder.encode(userChangeRegistrationDataDto.getPassword()));
			user.getUserRegistration().setFirstName(userChangeRegistrationDataDto.getFirstName().isBlank() ?
					user.getUserRegistration().getFirstName() : userChangeRegistrationDataDto.getFirstName());
			user.getUserRegistration().setLastName(userChangeRegistrationDataDto.getLastName().isBlank() ?
					user.getUserRegistration().getLastName() : userChangeRegistrationDataDto.getLastName());
			user.getUserRegistration().setEmail(userChangeRegistrationDataDto.getEmail().isBlank() ?
					user.getUserRegistration().getEmail() : userChangeRegistrationDataDto.getEmail());
			user.getUserRegistration().setDayBirthday(userChangeRegistrationDataDto.getDayBirthday() == null ?
					user.getUserRegistration().getDayBirthday() : userChangeRegistrationDataDto.getDayBirthday());
		}
		return "changes saved restart your account please";
	}
}
