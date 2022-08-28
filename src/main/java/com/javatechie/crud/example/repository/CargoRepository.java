package com.javatechie.crud.example.repository;

import com.javatechie.crud.example.entity.Cargo;
import com.javatechie.crud.example.entity.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CargoRepository extends InterfaceBaseRepository<Cargo, Integer> {
}
