package org.itstep.diploma.registration.repository;

import org.itstep.diploma.registration.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {
	Optional<UserRegistration> findByEmail(String email);
}
