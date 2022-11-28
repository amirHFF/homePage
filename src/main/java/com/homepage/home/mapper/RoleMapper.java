package com.homepage.home.mapper;

import com.homepage.home.dto.RoleDto;
import com.homepage.home.model.user.Role;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper extends MainMapper<Role, RoleDto> {
	RoleMapper getInstance = Mappers.getMapper(RoleMapper.class);
}
