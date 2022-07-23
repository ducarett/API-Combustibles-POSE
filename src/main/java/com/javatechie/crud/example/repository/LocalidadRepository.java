package com.javatechie.crud.example.repository;


import com.javatechie.crud.example.entity.Localidad;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocalidadRepository extends InterfaceBaseRepository<Localidad, Integer> {
    @Query(value = "SELECT distinct l FROM Localidad l INNER JOIN l.provincia p WHERE p.provinciaId = :provinciaId")
    List<Localidad> findAllForProvincia(Integer provinciaId);

    List<Localidad> findByFechaBajaIsNullAndHoraBajaIsNull();
}
