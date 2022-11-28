package com.homepage.home.service;

public interface IMainService<E> {
	E getById(long id);
	E save(E entity);
	E update(long id ,E entity);
	void delete(long id);
}
