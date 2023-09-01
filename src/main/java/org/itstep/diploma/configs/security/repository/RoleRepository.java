package org.itstep.diploma.configs.security.repository;

import org.itstep.diploma.configs.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
