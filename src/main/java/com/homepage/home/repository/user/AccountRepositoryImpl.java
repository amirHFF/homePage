package com.homepage.home.repository.user;

import com.homepage.home.model.user.Account;
import com.homepage.home.repository.MainRepositoryImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class AccountRepositoryImpl extends MainRepositoryImpl<Account> implements IAccountRepository {

    public AccountRepositoryImpl() {
        setClass(Account.class);
    }

    @PersistenceContext
    private EntityManager EM;

    @Override
    public Account findByUserName(String user) {
        Query query=EM.createQuery("select e from Account e where e.username =:user ");
        query.setParameter("user",user);

        return (Account) query.getSingleResult();
    }
}
