package com.javatechie.crud.example.utils.verificar.impl;

import com.javatechie.crud.example.repository.UsuarioBaseRepository;
import com.javatechie.crud.example.utils.verificar.interfaz.VerificarUsuarioEnBase;

import java.io.Serializable;

public abstract class VerificarLocalidadEnBaseImpl<E, ID extends Serializable> implements VerificarUsuarioEnBase {

    private UsuarioBaseRepository usuarioBaseRepository;

    public VerificarLocalidadEnBaseImpl(UsuarioBaseRepository usuarioBaseRepository) {
        this.usuarioBaseRepository = usuarioBaseRepository;
    }

    @Override
    public boolean buscarLegajo(Integer legajo) {
        return usuarioBaseRepository.findByLegajo(legajo) != null;
    }

    @Override
    public boolean buscarMail(String mail) {
        return usuarioBaseRepository.findByMail(mail) != null;
    }

    @Override
    public boolean buscarCelular(Integer celular) {
        return usuarioBaseRepository.findByCelular(celular) != null;
    }

}
