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

	public RoleService(IRoleRepository repository) {
		this.repository = repository;
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
		Role role =repository.save(RoleMapper.getInstance.dtoToEntity(dto));
		return RoleMapper.getInstance.entityToDto(role);
	}

	@Override
	public RoleDto update(long id, RoleDto dto) {
		Role role =repository.find(id);
		if (role !=null) {
			Role updatedRole = repository.update(RoleMapper.getInstance.dtoToEntity(dto));
		}
		return RoleMapper.getInstance.entityToDto(role);
	}

	@Override
	public void delete(long id) {

	}
}
