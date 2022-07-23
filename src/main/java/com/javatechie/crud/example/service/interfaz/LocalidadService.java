package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.dto.LocalidadDTO;
import com.javatechie.crud.example.entity.Localidad;

import java.util.List;

public interface LocalidadService extends BaseService<Localidad, Integer> {

    List<String> listLocalidades(Integer provinciaId) throws Exception;

    boolean bajaLocalidad(Integer id) throws Exception;

    String buscarPorId(Integer id) throws Exception;

    List<LocalidadDTO> ordenarLocalidades() throws Exception;

}
