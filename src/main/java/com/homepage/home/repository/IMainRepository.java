package com.homepage.home.repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface IMainRepository<E> {
    EntityManager getEntityManager();
    E find(long id);
    List<E> findAll();
    boolean save(E entity);
    boolean delete(long id);
    boolean update(E entity);
}
