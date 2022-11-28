package com.homepage.home.mapper;

import com.homepage.home.dto.AccountDto;
import com.homepage.home.model.user.Account;
import com.homepage.home.model.user.Role;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = RoleMapper.class ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper extends MainMapper<Account, AccountDto> {
	AccountMapper getInstance= Mappers.getMapper(AccountMapper.class);

}
