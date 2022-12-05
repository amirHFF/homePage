package com.homepage.home.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import javax.transaction.Transactional;
import java.util.List;

@Repository
public abstract class MainRepositoryImpl<E> implements IMainRepository<E> {

	private Class C;

	@PersistenceContext
	private EntityManager EM;

	public void setClass(Class C) {
		this.C = C;
	}

	@Override
	public E find(long id) {
		Object obj = EM.find(C, id);
		if (obj == null)
			return null;
		else
			return (E) obj;
	}

	@Override
	public List<E> findAll() {
		Query query = EM.createQuery("select e from " + C.getSimpleName() + " e");
		return query.getResultList();
	}

	@Transactional
	@Override
	public E save(E entity) {
		EM.persist(entity);
		return entity;
	}

	@Override
	public boolean delete(long id) {
		Object obj = find(id);
		try {
			EM.remove(obj);
		}catch (Exception ex){
			ex.printStackTrace();
		}

		return true;
	}

	@Transactional
	@Override
	public E update(E entity) {
		E updated=null;
		if (!EM.contains(entity)){
			throw new RuntimeException("update entity is currently detached");
		}
		else {
			updated=EM.merge(entity);
		}
		return updated;
	}

	@Override
	public EntityManager getEntityManager() {
		return EM;
	}
}
