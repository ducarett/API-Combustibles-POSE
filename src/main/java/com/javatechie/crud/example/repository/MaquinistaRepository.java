package com.javatechie.crud.example.repository;

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

    @Query(value = "select m from Maquinista m where m.fechaBaja is NOT null and m.horaBaja is not null")
    Page<Maquinista> findByFechaBajaIsNotNullAndHoraBajaIsNotNull(Pageable pageable);

    Maquinista findByLegajo(Integer legajo);

    Page<Maquinista> findByApellido(Pageable pageable, String apellido);

    Page<Maquinista> findByNombre(Pageable pageable, String nombre);

}
