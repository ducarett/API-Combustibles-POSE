package com.javatechie.crud.example.utils.processDto.interfaze;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface ProcessDtoService<E, ID extends Serializable> {

    List<E> listarActivos(Pageable pageable) throws Exception;

    List<E> listarTodos(Pageable pageable) throws Exception;

    E buscarPorNombre(String nombre) throws Exception;

    E buscarPorLegajo(Integer legajo) throws Exception;

    E buscarPorApellido(String apellido) throws Exception;

}
