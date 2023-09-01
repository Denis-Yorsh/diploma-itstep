package org.itstep.diploma.admin.service;

import org.itstep.diploma.admin.dto.AddDeleteRoleDto;

public interface AddDeleteRoleService {
	String addRole(AddDeleteRoleDto addDeleteRoleDto);

	String deleteRole(AddDeleteRoleDto addDeleteRoleDto);
}
