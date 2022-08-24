package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.dto.ObrasActivasDTO;
import com.javatechie.crud.example.entity.Obra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ObraService extends BaseService<Obra, Integer> {

    Obra editarObra(Integer id, Obra entity) throws Exception;

    Obra crearObra(Obra entity) throws Exception;

    boolean bajaObra(Integer id) throws Exception;

    ObrasActivasDTO getPorObraID(Integer id) throws Exception;

    List<ObrasActivasDTO> listProvincia(String provincia, Pageable pageable) throws Exception;

    List<ObrasActivasDTO> listDescripcion(String descripcion, Pageable pageable) throws Exception;

    List<ObrasActivasDTO> listLocalidad(String localidad, Pageable pageable) throws Exception;

    List<ObrasActivasDTO> listGerente(String gerente, Pageable pageable) throws Exception;

    List<ObrasActivasDTO> listJefe(String jefe, Pageable pageable) throws Exception;

    List<ObrasActivasDTO> listAdministrativo(String administrativo, Pageable pageable) throws Exception;

    Page<Obra> listInactivas(Pageable pageable) throws Exception;
}
