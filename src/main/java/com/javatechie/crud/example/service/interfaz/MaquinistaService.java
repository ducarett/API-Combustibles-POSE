package com.javatechie.crud.example.service.interfaz;


import com.javatechie.crud.example.dto.MaquinistaActivoDTO;
import com.javatechie.crud.example.dto.MaquinistaConsultaDTO;
import com.javatechie.crud.example.dto.MaquinistaDTO;
import com.javatechie.crud.example.entity.Maquinista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MaquinistaService extends BaseService<Maquinista, Integer> {
    boolean bajaMaquinista(Integer id, Integer adminId) throws Exception;

    Maquinista getEntity(Integer id) throws Exception;

    Page<Maquinista> listActivos(Pageable pageable) throws Exception;

    Maquinista getByApellido(String apellido) throws Exception;

    Maquinista getByNombre(String nombre) throws Exception;

    Maquinista getByLegajo(Integer legajo) throws Exception;

    Maquinista actualizarMaquinista(Integer id ,Maquinista maquinista,Integer adminId) throws Exception;

    Maquinista crearMaquinista(Maquinista maquinista,Integer adminId) throws Exception;

}
