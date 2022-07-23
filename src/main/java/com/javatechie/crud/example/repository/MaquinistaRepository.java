package com.javatechie.crud.example.repository;


import com.javatechie.crud.example.dto.MaquinistaActivoDTO;
import com.javatechie.crud.example.entity.Maquinista;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaquinistaRepository extends InterfaceBaseRepository<Maquinista, Integer> {

    List<Maquinista> findByFechaBajaIsNullAndHoraBajaIsNull();

    Maquinista findByLegajo(Integer legajo);

    Maquinista findByApellido(String apellido);

    Maquinista findByNombre(String nombre);

}
