package com.javatechie.crud.example.service.interfaz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E, ID extends Serializable> {

    List<E> findAll() throws Exception;

    Page<E> findAll(Pageable pageable) throws Exception;

    E getById(ID id) throws Exception;

    E save(E entity) throws Exception;

    E update(ID id, E entity) throws Exception;

    boolean inactive(ID id) throws Exception;

    E getEntity(E entity) throws Exception;

    Page<E> listGeneric(Page<E> entities) throws Exception;

}
