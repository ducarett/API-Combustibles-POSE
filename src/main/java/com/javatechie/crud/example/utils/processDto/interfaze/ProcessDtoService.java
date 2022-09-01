package com.javatechie.crud.example.utils.processDto.interfaze;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface ProcessDtoService<E, ID extends Serializable> {

    List<E> listarActivos(Pageable pageable) throws Exception;

    List<E> listarInactivos(Pageable pageable) throws Exception;

    List<E> listarTodos(Pageable pageable) throws Exception;

    List<E> listar(Page page) throws Exception;

}
