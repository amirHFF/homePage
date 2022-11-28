package com.homepage.home.service.userAccount;

import com.homepage.home.dto.AccountDto;
import com.homepage.home.service.IMainService;

public interface IAccountService extends IMainService<AccountDto> {
	void addRoleToUser(long id , String role);
	void loadAsEntity(long id);
}
