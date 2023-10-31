package org.itstep.diploma.configs.security.repository;

import org.itstep.diploma.configs.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	@Query("select distinct p from  User p left join fetch p.authorities")
	List<User> findAllUsers();
}
