package org.itstep.diploma.registration.repository;

import org.itstep.diploma.registration.entity.UsersRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRegistrationRepository extends JpaRepository<UsersRegistration, Long> {
}
