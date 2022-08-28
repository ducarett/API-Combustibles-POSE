package com.javatechie.crud.example.utils.complete.interfaz;

import java.io.Serializable;

public interface CompletarCampos<E,ID extends Serializable> {

    E alta(E entity, ID altaId) throws Exception;

    void baja(E entity, ID bajaId) throws Exception;

    E modificar(E entity, ID modId) throws Exception;
}
