package com.kts.cultural_content.service;

import java.util.List;

public interface ServiceInterface<T> {

    List<T> findAll();

    T findOne(Integer id);

    T create(T entity) throws Exception;

    T update(T entity, Integer id) throws Exception;

    void delete(Integer id) throws Exception;
}
