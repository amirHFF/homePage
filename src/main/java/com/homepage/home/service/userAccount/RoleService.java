package com.homepage.home.service.userAccount;

import com.homepage.home.dto.RoleDto;
import com.homepage.home.model.user.Role;
import com.homepage.home.repository.MainRepositoryImpl;
import com.homepage.home.repository.user.IAccountRepository;
import com.homepage.home.repository.user.IRoleRepository;
import com.homepage.home.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends MainService<RoleDto> implements IRoleService {

	private IRoleRepository repository;

	public RoleService(IRoleRepository repository) {
		this.repository = repository;
		setRepository(repository);
	}

	@Override
	public List<RoleDto> getRoleByUsername(String username) {
		repository.getRoleByUserName(username);
		return null;
	}
}
