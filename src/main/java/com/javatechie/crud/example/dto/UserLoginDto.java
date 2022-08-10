package com.javatechie.crud.example.dto;

import lombok.Data;

@Data
public class UserLoginDto {

    private int usuarioId;

    private String apellido;

    private String nombre;

    private String jwt;
}
