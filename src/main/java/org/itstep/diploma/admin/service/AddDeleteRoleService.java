package org.itstep.diploma.admin.service;

import org.itstep.diploma.admin.dto.AddDeleteRoleDto;
import org.itstep.diploma.configs.security.entity.Roles;

public interface AddDeleteRoleService {
	String addRole(AddDeleteRoleDto addDeleteRoleDto, Roles role);
	String deleteRole(AddDeleteRoleDto addDeleteRoleDto, Roles role);
}
