package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.entity.Obra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ObraService extends BaseService<Obra, Integer> {

    Obra editarObra(Integer id, Obra entity, Integer adminId) throws Exception;

    Obra crearObra(Obra entity, Integer adminID) throws Exception;

    boolean bajaObra(Integer id, Integer adminId) throws Exception;

    Obra getEntity(Integer id) throws Exception;

    Page<Obra> listAllObras(Pageable pageable) throws Exception;

    Page<Obra> listInactivos(Pageable pageable) throws Exception;

    Page<Obra> listActivos(Pageable pageable) throws Exception;

    Page<Obra> listProvincia(String provincia, Pageable pageable) throws Exception;

    Page<Obra> listDescripcion(String descripcion, Pageable pageable) throws Exception;

    Page<Obra> listLocalidad(String localidad, Pageable pageable) throws Exception;

    Page<Obra> listGerente(String gerente, Pageable pageable) throws Exception;

    Page<Obra> listJefe(String jefe, Pageable pageable) throws Exception;

    Page<Obra> listAdministrativo(String administrativo, Pageable pageable) throws Exception;
}
