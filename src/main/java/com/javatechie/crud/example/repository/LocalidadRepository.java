package com.javatechie.crud.example.repository;


import com.javatechie.crud.example.entity.Localidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocalidadRepository extends InterfaceBaseRepository<Localidad, Integer> {
    @Query(value = "SELECT distinct l FROM Localidad l INNER JOIN l.provincia p WHERE p.provinciaId = :provinciaId")
    Page<Localidad> findAllForProvincia(Integer provinciaId, Pageable pageable);

    Page<Localidad> findByFechaBajaIsNullAndHoraBajaIsNull(Pageable pageable);

    Page<Localidad> findByFechaBajaIsNotNullAndHoraBajaIsNotNull(Pageable pageable);
}
