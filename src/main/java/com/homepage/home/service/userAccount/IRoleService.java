package com.homepage.home.service.userAccount;

import com.homepage.home.dto.RoleDto;
import com.homepage.home.model.user.Role;
import com.homepage.home.service.IMainService;

import java.util.List;

public interface IRoleService extends IMainService<RoleDto> {
	List<RoleDto> getRoleByUsername(String username);
}
