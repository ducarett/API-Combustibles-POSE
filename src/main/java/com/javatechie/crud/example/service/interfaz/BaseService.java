package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<E, ID extends Serializable> {
    List<E> findAll() throws Exception;

    Page<E> findAll(Pageable pageable) throws Exception;
    E getById(ID id) throws Exception;

    E save(E entity) throws Exception;

    E update(ID id, E entity) throws Exception;

    boolean inactive(ID id) throws Exception;

}
