package com.homepage.home.mapper;

import com.homepage.home.dto.RoleDto;
import com.homepage.home.model.user.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = AccountMapper.class ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper extends MainMapper<Role, RoleDto> {
//	RoleMapper getInstance = Mappers.getMapper(RoleMapper.class);

	@Mapping(source = "accountId", target = "account.id")
	@Override
	Role dtoToEntity(RoleDto dto);

	@Mapping(source = "account.id",target = "accountId")
	@Override
	RoleDto entityToDto(Role entity);
}
