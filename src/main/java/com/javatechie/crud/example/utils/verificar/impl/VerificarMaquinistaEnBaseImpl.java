package com.javatechie.crud.example.utils.verificar.impl;

import com.javatechie.crud.example.repository.MaquinistaBaseRepository;
import com.javatechie.crud.example.utils.verificar.interfaz.VerificarMaquinistaEnBase;

import java.io.Serializable;

public abstract class VerificarMaquinistaEnBaseImpl<E, ID extends Serializable> implements VerificarMaquinistaEnBase {

    private MaquinistaBaseRepository maquinistaBaseRepository;

    public VerificarMaquinistaEnBaseImpl(MaquinistaBaseRepository maquinistaBaseRepository) {
        this.maquinistaBaseRepository = maquinistaBaseRepository;
    }

    @Override
    public boolean buscarLegajo(Integer legajo) {
        return maquinistaBaseRepository.findByLegajo(legajo) != null;
    }

    @Override
    public boolean compararLegajos(Integer id, Integer legajo) {
        return legajo != maquinistaBaseRepository.findById(id).get().getLegajo();
    }

}
