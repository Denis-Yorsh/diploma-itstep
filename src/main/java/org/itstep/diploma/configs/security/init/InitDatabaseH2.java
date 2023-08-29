package org.itstep.diploma.configs.security.init;

import lombok.RequiredArgsConstructor;
import org.itstep.diploma.configs.security.entity.Roles;
import org.itstep.diploma.configs.security.entity.Users;
import org.itstep.diploma.configs.security.repository.UserRepository;
import org.itstep.diploma.registration.entity.UsersRegistration;
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
	private final Users userAdmin;
	private final Users userUser;
	private final UsersRegistration usersRegistrationAdmin;
	private final UsersRegistration usersRegistrationUser;
	private final Roles roleAdminForAdmin;
	private final Roles roleWriterForAdmin;
	private final Roles roleUserForAdmin;
	private final Roles rolUserForUser;
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
	public void run(String... args) throws Exception {
		roleAdminForAdmin.setAuthority("ROLE_ADMIN");
		roleWriterForAdmin.setAuthority("ROLE_WRITER");
		roleUserForAdmin.setAuthority("ROLE_USER");
		rolUserForUser.setAuthority("ROLE_USER");

		usersRegistrationAdmin.setEmail(adminEmail);
		usersRegistrationUser.setEmail(userEmail);

		userAdmin.setUsername("admin");
		userAdmin.setPassword(passwordEncoder.encode(adminPassword));
		userUser.setUsername(userName);
		userUser.setPassword(passwordEncoder.encode(userPassword));

		userRepository.save(userAdmin);
		userRepository.save(userUser);

		userAdmin.addRole(roleAdminForAdmin, roleWriterForAdmin, roleUserForAdmin);
		userAdmin.addUsersRegistration(usersRegistrationAdmin);
		userUser.addRole(rolUserForUser);
		userUser.addUsersRegistration(usersRegistrationUser);
	}
}
