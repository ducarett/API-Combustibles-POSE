package com.javatechie.crud.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TIPO_USUARIO")
public class TipoUsuario implements Serializable {

    @Id
    @Column(name = "ID_TIPO_USUARIO")
    private int tipoUsuarioId;

    @Column(name = "DESC_TIPO_USUARIO")
    private String descripcion;
}
