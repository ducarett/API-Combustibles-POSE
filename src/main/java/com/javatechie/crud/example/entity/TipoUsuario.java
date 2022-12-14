package com.javatechie.crud.example.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TIPO_USUARIO")
@EqualsAndHashCode(of = {"tipoUsuarioId"}, callSuper = false)
public class TipoUsuario implements Serializable {

    @Id
    @Column(name = "ID_TIPO_USUARIO")
    private int tipoUsuarioId;

    @Column(name = "DESC_TIPO_USUARIO")
    private String descripcion;

}
