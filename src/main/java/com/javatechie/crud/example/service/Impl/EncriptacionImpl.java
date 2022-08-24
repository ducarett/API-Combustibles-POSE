package com.javatechie.crud.example.service.Impl;


import com.javatechie.crud.example.service.interfaz.Encriptacion;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncriptacionImpl implements Encriptacion {

    public String encriptarClave(String password) {
        String clave = password.toUpperCase();
        return BCrypt.hashpw(clave, BCrypt.gensalt());
    }

    public boolean descencriptarClave(String passwordActual, String passwordEnBase) {
        return BCrypt.checkpw(passwordActual, passwordEnBase) ? true : false;
    }
}
