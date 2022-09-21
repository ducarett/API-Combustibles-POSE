package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.dto.LocalidadDTO;
import com.javatechie.crud.example.entity.Localidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LocalidadService extends BaseService<Localidad, Integer> {

    Localidad crearLocalidad(Localidad localidad, Integer adminId) throws Exception;

    Localidad editarLocalidad(Integer id, Localidad localidad, Integer adminId) throws Exception;

    boolean bajaLocalidad(Integer id, Integer adminId) throws Exception;

    Page<Localidad> listarLocalidadesPorProvId(Integer provinciaId, Pageable pageable) throws Exception;

    Page<Localidad> listarLocalidadesPorNombre(String nombre, Pageable pageable) throws Exception;

    Page<Localidad> listAllObras(Pageable pageable) throws Exception;

    Page<Localidad> listInactivos(Pageable pageable) throws Exception;

    Page<Localidad> listActivos(Pageable pageable) throws Exception;

    Localidad getPorId(Integer id) throws Exception;

}
