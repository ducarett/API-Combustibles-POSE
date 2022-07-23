package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.dto.ObrasActivasDTO;
import com.javatechie.crud.example.entity.Obra;

import java.util.List;

public interface ObraService extends BaseService<Obra, Integer> {

    boolean bajaObra(Integer id) throws Exception;

    ObrasActivasDTO getPorObraID(Integer id) throws Exception;

    List<ObrasActivasDTO> listProvincia(String provincia) throws Exception;

    List<ObrasActivasDTO> listDescripcion(String descripcion) throws Exception;

    List<ObrasActivasDTO> listLocalidad(String localidad) throws Exception;

    List<ObrasActivasDTO> listGerente(String gerente) throws Exception;

    List<ObrasActivasDTO> listJefe(String jefe) throws Exception;

    List<ObrasActivasDTO> listAdministrativo(String administrativo) throws Exception;

    List<Obra> listInactivas() throws Exception;
}
