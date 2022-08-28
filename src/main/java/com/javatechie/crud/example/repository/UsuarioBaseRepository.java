package com.javatechie.crud.example.repository;

import com.javatechie.crud.example.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioBaseRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByLegajo(Integer legajo);
    Usuario findByMail(String mail);
    Usuario findByCelular(Integer celular);
}
