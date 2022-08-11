package com.javatechie.crud.example.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CARGOS")
@EqualsAndHashCode(of = {"cargoId"}, callSuper = false)
public class Cargo implements Serializable {

    @Id
    @Column(name = "ID_CARGOS")
    private int cargoId;

    @Column(name = "DESC_CARGO")
    private String descripcionCargo;

}
