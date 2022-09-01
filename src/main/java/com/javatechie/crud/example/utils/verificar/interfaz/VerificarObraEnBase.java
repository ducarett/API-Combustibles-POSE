package com.javatechie.crud.example.utils.verificar.interfaz;

import com.javatechie.crud.example.entity.Obra;

public interface VerificarObraEnBase {
    boolean buscarCodigo(Integer codigo);

    boolean compararCodigos(Integer codigoNuevo, Obra obra);

}
