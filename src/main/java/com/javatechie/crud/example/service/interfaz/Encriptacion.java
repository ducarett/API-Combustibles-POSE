package com.javatechie.crud.example.service.interfaz;

public interface Encriptacion {

    String encriptarClave(String pass);

    boolean descencriptarClave(String passwordActual, String passwordEnBase);
}
