package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.entity.Usuario;

public interface ProcessUsuarioService {

    Usuario actualizarUsuario(Integer id, Usuario usuario) throws Exception;

}
