package com.homepage.home.mapper;

import com.homepage.home.dto.AccountDto;
import com.homepage.home.dto.AccountPatchRequestDto;
import com.homepage.home.dto.AccountResponseDto;
import com.homepage.home.dto.RoleDto;
import com.homepage.home.model.user.Account;
import com.homepage.home.model.user.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = RoleMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class AccountMapper implements MainMapper<Account, AccountDto> {

	@Mapping(source = "authorityList", target = "authorityList", qualifiedByName = "roleMapToString")
	public abstract AccountResponseDto dtoToResponse(AccountDto dto);

	@Mapping(source = "authority", target = "authorityList", qualifiedByName = "authorityToList")
	public abstract AccountDto patchToDto(AccountPatchRequestDto patchRequestDto);

	@Named("roleMapToString")
	List<String> roleMapToString(List<RoleDto> roleDtoList) {
		return roleDtoList.stream().map(RoleDto::getAuthority).collect(Collectors.toList());
	}

	@Named("authorityToList")
	List<RoleDto> stringMapToRole(String authority) {
		if (authority != null)
			return Collections.singletonList(new RoleDto(authority));
		else
			return null;
	}
}
