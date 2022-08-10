package com.javatechie.crud.example.entity;

import javax.persistence.*;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OBRAS")
@EqualsAndHashCode(of = {"obraId"}, callSuper = false)
public class Obra extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "ID_OBRA")
    private int obraId;

    @Column(name = "NOMBRE_OBRA")
    private String nombreObra;

    @Column(name = "DESC_OBRA")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROVINCIA")
    private Provincia provincia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LOCALIDAD")
    private Localidad localidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GERENTE")
    private Usuario gerente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_JEFE")
    private Usuario jefe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ADM")
    private Usuario admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ALTA")
    private Usuario usuarioAlta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_MOD")
    private Usuario usuarioMod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_BAJA")
    private Usuario usuarioBaja;

}
