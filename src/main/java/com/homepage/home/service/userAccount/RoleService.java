package com.homepage.home.service.userAccount;

import com.homepage.home.dto.RoleDto;
import com.homepage.home.mapper.RoleMapper;
import com.homepage.home.model.user.Role;
import com.homepage.home.repository.user.IRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

	private IRoleRepository repository;
	private RoleMapper roleMapper;

	public RoleService(RoleMapper roleMapper,IRoleRepository repository) {
		this.repository = repository;
		this.roleMapper = roleMapper;
	}

	@Override
	public List<RoleDto> getRoleByUsername(String username) {
		repository.getRoleByUserName(username);
		return null;
	}

	@Override
	public RoleDto getById(long id) {
		return null;
	}

	@Override
	public RoleDto save(RoleDto dto) {
		Role role =repository.save(roleMapper.dtoToEntity(dto));
		return roleMapper.entityToDto(role);
	}

	@Override
	public RoleDto update(Object id, RoleDto dto) {
		Role role =repository.find((Long) id);
		if (role !=null) {
			Role updatedRole = repository.update(roleMapper.dtoToEntity(dto));
		}
		return roleMapper.entityToDto(role);
	}

	@Override
	public void delete(long id) {

	}
}
