package com.javatechie.crud.example.repository;

import com.javatechie.crud.example.entity.Obra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ObraRepository extends InterfaceBaseRepository<Obra, Integer> {

    @Override
    Optional<Obra> findById(Integer id);

    Page<Obra> findByFechaBajaIsNullAndHoraBajaIsNull(Pageable pageable);

    Page<Obra> findByFechaBajaIsNotNullAndUsuarioBajaIsNotNull(Pageable pageable);

    @Query(value = "SELECT distinct o FROM Obra o INNER JOIN o.provincia p WHERE p.descriptionProvincia = :provincia and o.fechaBaja is null")
    Page<Obra> findByProvincia(String provincia,Pageable pageable);

    @Query(value = "SELECT o FROM Obra o  WHERE o.descripcion LIKE %:descripcion% and o.fechaBaja is null")
    Page<Obra> findByDescripcion(String descripcion,Pageable pageable);

    @Query("SELECT o FROM Obra o WHERE o.gerente.usuarioId = ?1 and o.fechaBaja is null")
    Page<Obra> findByGerente(Integer gerenteId,Pageable pageable);

    @Query("SELECT o FROM Obra o WHERE o.jefe.usuarioId = ?1 and o.fechaBaja is null")
    Page<Obra> findByJefe(Integer jefeId,Pageable pageable);

    @Query("SELECT o FROM Obra o WHERE o.admin.usuarioId = ?1 and o.fechaBaja is null")
    Page<Obra> findByAdministrativo(Integer administrativoId,Pageable pageable);

    @Query(value = "SELECT distinct o FROM Obra o INNER JOIN o.localidad l WHERE l.descripcionLocalidad = :localidad and o.fechaBaja is null")
    Page<Obra> findByLocalidad(String localidad,Pageable pageable);


}
