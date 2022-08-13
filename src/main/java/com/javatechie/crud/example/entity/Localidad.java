package com.javatechie.crud.example.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LOCALIDADES")
@EqualsAndHashCode(of = {"id"},callSuper = false)
public class Localidad extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "ID_LOCALIDAD")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "ID_PROVINCIA")
    private Provincia provincia;

    @Column(name = "DESC_LOCALIDAD")
    private String descripcionLocalidad;

    @ManyToOne()
    @JoinColumn(name = "USER_ALTA")
    private Usuario usuarioAlta;

    @ManyToOne()
    @JoinColumn(name = "USER_MOD")
    private Usuario usuarioMod;

    @ManyToOne()
    @JoinColumn(name = "USER_BAJA")
    private Usuario usuarioBaja;

    @Column(name = "CP")
    private Integer codigoPostal;

}
