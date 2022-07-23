package com.javatechie.crud.example.repository;


import com.javatechie.crud.example.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends InterfaceBaseRepository<Usuario, Integer> {

    Usuario findByLogin(String login);

    List<Usuario> findByCelular(Integer login);

    @Query(value = "SELECT * FROM usuarios u INNER JOIN cargos C ON U.ID_CARGO = C.ID_CARGOS WHERE C.DESC_CARGO = 'GERENTE' AND U.NOMBRE = :nomApell OR U.APELLIDO = :nomApell", nativeQuery = true)
    Usuario findUserIsGerente(@Param("nomApell") String dato);
    @Query(value = "SELECT * FROM usuarios u INNER JOIN cargos C ON U.ID_CARGO = C.ID_CARGOS WHERE C.DESC_CARGO = 'JEFE' AND U.NOMBRE = :nomApell OR U.APELLIDO = :nomApell", nativeQuery = true)
    Usuario findUserIsJefe(@Param("nomApell") String dato);

    @Query(value = "SELECT * FROM usuarios u INNER JOIN cargos C ON U.ID_CARGO = C.ID_CARGOS WHERE C.DESC_CARGO = 'ADMINISTRATIVO' AND U.NOMBRE = :nomApell OR U.APELLIDO = :nomApell", nativeQuery = true)
    Usuario findUserIsAdmin(@Param("nomApell") String dato);

    List<Usuario> findByMail(String login);

    List<Usuario> findByNombre(String nombre);

    List<Usuario> findByApellido(String apellido);

    @Query(value = "SELECT distinct u FROM Usuario u INNER JOIN u.cargo c WHERE c.descripcionCargo = :cargo")
    List<Usuario> findByCargo(@Param("cargo") String cargos);

    @Query(value = "SELECT u FROM Usuario u  WHERE u.cargo.cargoId = 10 AND u.fechaBaja is null ")
    List<Usuario> findListGerente();

    @Query(value = "SELECT u FROM Usuario u  WHERE u.cargo.cargoId = 20 AND u.fechaBaja is null ")
    List<Usuario> findListJefes();

    @Query(value = "SELECT u FROM Usuario u  WHERE u.cargo.cargoId = 30 AND u.fechaBaja is null ")
    List<Usuario> findListAdministrativo();

    List<Usuario> findByLegajo(Integer nombre);

    List<Usuario> findByFechaBajaIsNullAndHoraBajaIsNull();

    List<Usuario> findByFechaBajaIsNotNullAndHoraBajaIsNotNull();

}