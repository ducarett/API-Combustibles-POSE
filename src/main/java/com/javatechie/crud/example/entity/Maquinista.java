package com.javatechie.crud.example.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MAQUINISTAS")
@EqualsAndHashCode(of = {"maquinistaId"}, callSuper = false)
public class Maquinista extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "MAQUINISTA_ID")
    private int maquinistaId;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "LEGAJO")
    private int legajo;

    @ManyToOne()
    @JoinColumn(name = "USER_ALTA")
    private Usuario usuarioAlta;

    @ManyToOne()
    @JoinColumn(name = "USER_MOD")
    private Usuario usuarioMod;

    @ManyToOne()
    @JoinColumn(name = "USER_BAJA")
    private Usuario usuarioBaja;

}
