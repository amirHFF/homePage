package com.homepage.home.repository.user;

import com.homepage.home.model.user.Account;
import com.homepage.home.repository.IMainRepository;

import java.util.List;

public interface IAccountRepository extends IMainRepository<Account> {
    Account findByUserName(String user);
}
