package com.homepage.home.repository.user;

import com.homepage.home.model.user.Role;
import com.homepage.home.repository.MainRepositoryImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleRepository extends MainRepositoryImpl<Role> implements IRoleRepository {
	@PersistenceContext
	private EntityManager EM;
	@Override
	public List<Role> getRoleByUserName(String username) {
		Query query=EM.createQuery("select e from Role e where e.account.userName = : username");
		query.setParameter("username",username);
		return (List<Role>) query.getResultList();
	}
}
