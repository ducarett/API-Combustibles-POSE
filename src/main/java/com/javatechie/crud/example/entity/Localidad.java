package com.javatechie.crud.example.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LOCALIDADES")
public class Localidad extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "ID_LOCALIDAD")
    private int localidadId;

    @ManyToOne
    @JoinColumn(name = "ID_PROVINCIA")
    private Provincia provincia;

    @Column(name = "DESC_LOCALIDAD")
    private String descripcionLocalidad;

    @ManyToOne
    @JoinColumn(name = "USER_ALTA")
    private Usuario usuarioAlta;

    @ManyToOne
    @JoinColumn(name = "USER_MOD")
    private Usuario usuarioMod;

    @ManyToOne
    @JoinColumn(name = "USER_BAJA")
    private Usuario usuarioBaja;

}
