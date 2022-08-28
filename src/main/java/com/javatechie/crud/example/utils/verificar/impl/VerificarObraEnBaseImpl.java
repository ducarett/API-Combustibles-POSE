package com.javatechie.crud.example.utils.verificar.impl;

import com.javatechie.crud.example.repository.ObraBaseRepository;
import com.javatechie.crud.example.utils.verificar.interfaz.VerificarObraEnBase;

import java.io.Serializable;

public abstract class VerificarObraEnBaseImpl<E, ID extends Serializable> implements VerificarObraEnBase {

    private ObraBaseRepository obraBaseRepository;

    public VerificarObraEnBaseImpl(ObraBaseRepository obraBaseRepository) {
        this.obraBaseRepository = obraBaseRepository;
    }

    @Override
    public boolean buscarCodigo(Integer codigo) {
        return obraBaseRepository.findByCodigoObra(codigo) != null;
    }
}
