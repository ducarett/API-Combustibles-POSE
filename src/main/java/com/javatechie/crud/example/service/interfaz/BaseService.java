package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.entity.Usuario;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<E, ID extends Serializable> {
    List<E> findAll() throws Exception;

    E findById(ID id) throws Exception;

    E save(E entity) throws Exception;

    E update(ID id, E entity) throws Exception;

    boolean inactive(ID id) throws Exception;

}
