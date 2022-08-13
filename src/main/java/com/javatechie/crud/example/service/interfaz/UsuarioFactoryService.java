package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.entity.Usuario;

public interface UsuarioFactoryService {

    Usuario actualizarUsuario(Integer id, Integer adminId, Usuario usuario) throws Exception;

    Usuario crearUsuario(Usuario usuario, Integer adminId) throws Exception;

}
