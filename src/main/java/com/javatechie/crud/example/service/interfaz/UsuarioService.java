package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.dto.UserDTO;
import com.javatechie.crud.example.entity.Usuario;

import java.util.List;

public interface UsuarioService extends BaseService<Usuario, Integer> {

    Usuario getPorLogin(String login) throws Exception;

    UserDTO buscarPorLogin(String login) throws Exception;

    List<UserDTO> listarPorNombre(String nombre) throws Exception;

    List<UserDTO> listarPorApellido(String apellido) throws Exception;

    List<UserDTO> listarPorCargo(String cargo) throws Exception;

    List<UserDTO> listarPorLegajo(Integer legajo) throws Exception;

    boolean bajaUsuario(Integer id) throws Exception;

    List<Usuario> listarActivos() throws Exception;

    List<Usuario> listarInactivos() throws Exception;

    List<String> listaGerentes() throws Exception;

    List<String> listaJefes() throws Exception;

    List<String> listaAdministrativos() throws Exception;
}
