package com.homepage.home.repository.user;

import com.homepage.home.model.user.Role;
import com.homepage.home.repository.IMainRepository;

import java.util.List;

public interface IRoleRepository extends IMainRepository<Role> {
	List<Role> getRoleByUserName(String username);
}
