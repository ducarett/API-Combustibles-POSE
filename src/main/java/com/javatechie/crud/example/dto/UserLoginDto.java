package com.javatechie.crud.example.dto;

import com.javatechie.crud.example.entity.TipoUsuario;
import lombok.Data;

@Data
public class UserLoginDto {

    private int usuarioId;

    private String apellido;

    private String nombre;

    private TipoUsuario tipoUsuario;

    private String jwt;
}
