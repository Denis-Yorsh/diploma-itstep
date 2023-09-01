package org.itstep.diploma.configs.security.init;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.configs.security.entity.Role;
import org.itstep.diploma.configs.security.entity.User;
import org.itstep.diploma.configs.security.repository.UserRepository;
import org.itstep.diploma.registration.entity.UserRegistration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Profile("developerH2")
public class InitDatabaseH2 implements CommandLineRunner {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final User userAdmin;
	private final User userUser;
	private final UserRegistration userRegistrationAdmin;
	private final UserRegistration userRegistrationUser;
	private final Role roleAdminForAdmin;
	private final Role roleWriterForAdmin;
	private final Role roleUserForAdmin;
	private final Role rolUserForUser;
	@Value("${spring.security.test.admin.password:admin}")
	private String adminPassword;
	@Value("${spring.security.test.admin.email:test_admin@test.com}")
	private String adminEmail;
	@Value("${spring.security.test.user.name:user}")
	private String userName;
	@Value("${spring.security.test.user.password:user}")
	private String userPassword;
	@Value("${spring.security.test.user.email:test_user@test.com}")
	private String userEmail;

	@Transactional
	@Override
	public void run(String... args) {
		roleAdminForAdmin.setAuthority("ROLE_ADMIN");
		roleWriterForAdmin.setAuthority("ROLE_WRITER");
		roleUserForAdmin.setAuthority("ROLE_USER");
		rolUserForUser.setAuthority("ROLE_USER");

		userRegistrationAdmin.setEmail(adminEmail);
		userRegistrationUser.setEmail(userEmail);

		userAdmin.setUsername("admin");
		userAdmin.setPassword(passwordEncoder.encode(adminPassword));
		userUser.setUsername(userName);
		userUser.setPassword(passwordEncoder.encode(userPassword));

		userRepository.save(userAdmin);
		userRepository.save(userUser);

		userAdmin.addRole(roleAdminForAdmin, roleWriterForAdmin, roleUserForAdmin);
		userAdmin.addUsersRegistration(userRegistrationAdmin);
		userUser.addRole(rolUserForUser);
		userUser.addUsersRegistration(userRegistrationUser);
	}
}
