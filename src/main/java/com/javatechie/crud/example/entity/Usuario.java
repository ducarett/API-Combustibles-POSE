package com.javatechie.crud.example.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USUARIOS")
public class Usuario extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "USER_ID")
    private int usuarioId;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "CELULAR")
    private int celular;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "LEGAJO")
    private int legajo;

    @ManyToOne
    @JoinColumn(name = "ID_CARGO")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "TIPO_USUARIO")
    private TipoUsuario tipoUsuario;


    @Column(name = "USER_ALTA")
    private Integer usuarioAlta;


    @Column(name = "USER_MOD")
    private Integer usuarioMod;


    @Column(name = "USER_BAJA")
    private Integer usuarioBaja;

}
