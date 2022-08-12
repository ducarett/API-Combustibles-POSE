package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.entity.Usuario;

public interface UsuarioFactoryService {

    Usuario actualizarUsuario(Integer id, Usuario usuario) throws Exception;

}
