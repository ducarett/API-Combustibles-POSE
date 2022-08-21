package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.dto.UserDTO;
import com.javatechie.crud.example.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService extends BaseService<Usuario, Integer> {

    Usuario getPorLogin(String login) throws Exception;

    UserDTO buscarPorLogin(String login) throws Exception;

    List<UserDTO> listarPorNombre(String nombre,Pageable pageable) throws Exception;

    List<UserDTO> listarPorApellido(String apellido,Pageable pageable) throws Exception;

    List<UserDTO> listarPorCargo(String cargo,Pageable pageable) throws Exception;

    UserDTO buscarPorLegajo(Integer legajo) throws Exception;

    boolean bajaUsuario(Integer id) throws Exception;

    Page<Usuario> listarActivos(Pageable pageable) throws Exception;

    Page<Usuario> listarInactivos(Pageable pageable) throws Exception;

    List<UserDTO> listaGerentes(Pageable pageable) throws Exception;

    List<UserDTO> listaJefes(Pageable pageable) throws Exception;

    List<UserDTO> listaAdministrativos(Pageable pageable) throws Exception;

    Usuario updateUser(Integer id, Usuario usuario, Integer adminId) throws Exception;
}
