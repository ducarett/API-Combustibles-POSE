package com.javatechie.crud.example.entity;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OBRAS")
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_PROVINCIA")
    private Provincia provincia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_LOCALIDAD")
    private Localidad localidad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_GERENTE")
    private Usuario gerente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_JEFE")
    private Usuario jefe;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_ADM")
    private Usuario admin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ALTA")
    private Usuario usuarioAlta;

    @ManyToOne
    @JoinColumn(name = "USER_MOD")
    private Usuario usuarioMod;

    @ManyToOne
    @JoinColumn(name = "USER_BAJA")
    private Usuario usuarioBaja;

}
