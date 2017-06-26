package com.itacademy.dao.common;

import com.itacademy.entity.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {

    Long save(T entity);

    T findById(Long id);

    T findByName(String name);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();

}
