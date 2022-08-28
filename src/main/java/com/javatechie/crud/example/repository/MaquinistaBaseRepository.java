package com.javatechie.crud.example.repository;

import com.javatechie.crud.example.entity.Maquinista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaquinistaBaseRepository extends JpaRepository<Maquinista, Integer> {
    Maquinista findByLegajo(Integer legajo);
}
