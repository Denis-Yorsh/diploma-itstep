package org.itstep.diploma.admin.service;

import org.itstep.diploma.admin.dto.AddDeleteRoleDto;
import org.itstep.diploma.configs.security.entity.Role;

public interface AddDeleteRoleService {
	String addRole(AddDeleteRoleDto addDeleteRoleDto, Role role);

	String deleteRole(AddDeleteRoleDto addDeleteRoleDto, Role role);
}
