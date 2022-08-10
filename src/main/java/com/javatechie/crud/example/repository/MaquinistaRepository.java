package com.javatechie.crud.example.repository;


import com.javatechie.crud.example.dto.MaquinistaActivoDTO;
import com.javatechie.crud.example.entity.Maquinista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaquinistaRepository extends InterfaceBaseRepository<Maquinista, Integer> {

    List<Maquinista> findByFechaBajaIsNullAndHoraBajaIsNull();

    @Query(value = "select m from Maquinista m where m.fechaBaja is null and m.horaBaja is null")
    Page<Maquinista> findByFechaBajaIsNullAndHoraBajaIsNull(Pageable pageable);

    Maquinista findByLegajo(Integer legajo);

    Maquinista findByApellido(String apellido);

    Maquinista findByNombre(String nombre);

}
