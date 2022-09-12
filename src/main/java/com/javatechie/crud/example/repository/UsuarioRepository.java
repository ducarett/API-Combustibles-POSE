package com.javatechie.crud.example.repository;


import com.javatechie.crud.example.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends InterfaceBaseRepository<Usuario, Integer> {

    Usuario findByLogin(String login);

    List<Usuario> findByCelular(Long login);

    @Query(value = "SELECT * FROM usuarios u INNER JOIN cargos C ON U.ID_CARGO = C.ID_CARGOS WHERE C.DESC_CARGO = 'GERENTE' AND U.NOMBRE = :nomApell OR U.APELLIDO = :nomApell", nativeQuery = true)
    Usuario findUserIsGerente(@Param("nomApell") String dato);

    @Query(value = "SELECT * FROM usuarios u INNER JOIN cargos C ON U.ID_CARGO = C.ID_CARGOS WHERE C.DESC_CARGO = 'JEFE' AND U.NOMBRE = :nomApell OR U.APELLIDO = :nomApell", nativeQuery = true)
    Usuario findUserIsJefe(@Param("nomApell") String dato);

    @Query(value = "SELECT * FROM usuarios u INNER JOIN cargos C ON U.ID_CARGO = C.ID_CARGOS WHERE C.DESC_CARGO = 'ADMINISTRATIVO' AND U.NOMBRE = :nomApell OR U.APELLIDO = :nomApell", nativeQuery = true)
    Usuario findUserIsAdmin(@Param("nomApell") String dato);

    List<Usuario> findByMail(String login);

    @Query(value = "Select u from Usuario u WHERE u.nombre LIKE %:filtro%")
    Page<Usuario> findListByNombre(@Param("filtro") String filtro, Pageable pageable);

    @Query(value = "Select u from Usuario u WHERE u.apellido LIKE %:filtro%")
    Page<Usuario> findListByApellido(@Param("filtro") String filtro, Pageable pageable);

    @Query(value = "SELECT distinct u FROM Usuario u INNER JOIN u.cargo c WHERE c.descripcionCargo LIKE %:filtro%")
    Page<Usuario> findListByCargo(@Param("filtro") String filtro, Pageable pageable);

    @Query(value = "SELECT u FROM Usuario u  WHERE u.cargo.cargoId = 20 AND u.fechaBaja is null ")
    Page<Usuario> findListGerente(Pageable pageable);

    @Query(value = "SELECT u FROM Usuario u  WHERE u.cargo.cargoId = 10 AND u.fechaBaja is null ")
    Page<Usuario> findListJefes(Pageable pageable);

    @Query(value = "SELECT u FROM Usuario u  WHERE u.cargo.cargoId = 30 AND u.fechaBaja is null ")
    Page<Usuario> findListAdministrativo(Pageable pageable);

    @Query(value = "SELECT u FROM Usuario u  WHERE u.cargo.descripcionCargo Like %:cargo% or u.apellido LIKE %:apellido% OR u.nombre LIKE %:nombre% OR u.login LIKE %:userName% OR u.legajo = :legajo")
    Page<Usuario> findListBuscados(Pageable page, @Param("nombre") String nombre,
                                   @Param("apellido") String apellido, @Param("userName") String userName,
                                   @Param("legajo") Integer legajo, @Param("cargo") String cargo);

    Usuario findByLegajo(Integer legajo);

    Page<Usuario> findByFechaBajaIsNullAndHoraBajaIsNull(Pageable pageable);

    Page<Usuario> findByFechaBajaIsNotNullAndHoraBajaIsNotNull(Pageable pageable);

}