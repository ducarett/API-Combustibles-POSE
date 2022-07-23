package com.javatechie.crud.example.repository;

import com.javatechie.crud.example.entity.Obra;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ObraRepository extends InterfaceBaseRepository<Obra, Integer> {

    @Override
    Optional<Obra> findById(Integer id);

    List<Obra> findByFechaBajaIsNullAndHoraBajaIsNull();

    List<Obra> findByFechaBajaIsNotNullAndUsuarioBajaIsNotNull();

    @Query(value = "SELECT distinct o FROM Obra o INNER JOIN o.provincia p WHERE p.descriptionProvincia = :provincia and o.fechaBaja is null")
    List<Obra> findByProvincia(String provincia);

    @Query(value = "SELECT o FROM Obra o  WHERE o.descripcion LIKE %:descripcion% and o.fechaBaja is null")
    List<Obra> findByDescripcion(String descripcion);

    @Query("SELECT o FROM Obra o WHERE o.gerente.usuarioId = ?1 and o.fechaBaja is null")
    List<Obra> findByGerente(Integer gerenteId);

    @Query("SELECT o FROM Obra o WHERE o.jefe.usuarioId = ?1 and o.fechaBaja is null")
    List<Obra> findByJefe(Integer jefeId);

    @Query("SELECT o FROM Obra o WHERE o.admin.usuarioId = ?1 and o.fechaBaja is null")
    List<Obra> findByAdministrativo(Integer administrativoId);

    @Query(value = "SELECT distinct o FROM Obra o INNER JOIN o.localidad l WHERE l.descripcionLocalidad = :localidad and o.fechaBaja is null")
    List<Obra> findByLocalidad(String localidad);


}
