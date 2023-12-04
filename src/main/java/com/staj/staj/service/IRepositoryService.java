package com.staj.staj.service;

import java.util.List;

public interface IRepositoryService<T>{
    T add(T entity);
    List<T> getAll();
    T getById(Long id);
    boolean delete(Long id);
    T update(Long id,T entity);
}

