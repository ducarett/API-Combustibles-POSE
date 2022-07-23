package com.javatechie.crud.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EntityBase implements Serializable {

    @Column(name = "FECHA_ALTA")
    private LocalDate fechaAlta;

    @Column(name = "HORA_ALTA")
    private LocalDateTime horaAlta;

    @Column(name = "FECHA_MOD")
    private LocalDate fechaMod;

    @Column(name = "HORA_MOD")
    private LocalDateTime horaMod;

    @Column(name = "FECHA_BAJA")
    private LocalDate fechaBaja;

    @Column(name = "HORA_BAJA")
    private LocalDateTime horaBaja;

}