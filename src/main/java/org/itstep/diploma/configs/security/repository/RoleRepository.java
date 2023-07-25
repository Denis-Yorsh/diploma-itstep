package org.itstep.diploma.configs.security.repository;

import org.itstep.diploma.configs.security.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {
}
