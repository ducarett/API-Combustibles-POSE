package com.javatechie.crud.example.service.interfaz;


import com.javatechie.crud.example.dto.MaquinistaActivoDTO;
import com.javatechie.crud.example.dto.MaquinistaConsultaDTO;
import com.javatechie.crud.example.entity.Maquinista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MaquinistaService extends BaseService<Maquinista, Integer> {
    boolean bajaMaquinista(Integer id) throws Exception;

   // List<Maquinista> listActivos() throws Exception;

    Page<Maquinista> listActivos(Pageable pageable) throws Exception;

    MaquinistaActivoDTO buscarPorApellido(String apellido) throws Exception;

    MaquinistaActivoDTO buscarPorNombre(String nombre) throws Exception;

    MaquinistaActivoDTO buscarPorLegajo(Integer legajo) throws Exception;

}
