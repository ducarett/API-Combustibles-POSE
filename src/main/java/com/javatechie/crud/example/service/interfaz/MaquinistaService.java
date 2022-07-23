package com.javatechie.crud.example.service.interfaz;


import com.javatechie.crud.example.dto.MaquinistaActivoDTO;
import com.javatechie.crud.example.dto.MaquinistaConsultaDTO;
import com.javatechie.crud.example.entity.Maquinista;

import java.util.List;

public interface MaquinistaService extends BaseService<Maquinista, Integer> {
    boolean bajaMaquinista(Integer id) throws Exception;

    List<Maquinista> listActivos() throws Exception;

    MaquinistaActivoDTO buscarPorApellido(String apellido) throws Exception;

    MaquinistaActivoDTO buscarPorNombre(String nombre) throws Exception;

    MaquinistaActivoDTO buscarPorLegajo(Integer legajo) throws Exception;

}
