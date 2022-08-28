package com.javatechie.crud.example.service.interfaz;

import java.io.Serializable;

public interface ProcessService<E, ID extends Serializable> {

    E crear(E maquinista, ID adminId) throws Exception;

    E actualizar(ID id, E Maquinista, ID adminId) throws Exception;

    Boolean baja(ID id, ID adminId) throws Exception;

    E buscarPorId(ID id) throws Exception;
}
