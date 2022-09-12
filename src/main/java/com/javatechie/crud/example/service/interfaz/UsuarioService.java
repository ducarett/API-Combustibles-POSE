package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.dto.UsuarioDTO;
import com.javatechie.crud.example.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService extends BaseService<Usuario, Integer> {

    Usuario getPorLogin(String login) throws Exception;

    UsuarioDTO buscarPorLogin(String login) throws Exception;

    List<UsuarioDTO> listarPorNombre(String nombre, Pageable pageable) throws Exception;

    List<UsuarioDTO> listarPorApellido(String apellido, Pageable pageable) throws Exception;

    List<UsuarioDTO> listarPorCargo(String cargo, Pageable pageable) throws Exception;

    UsuarioDTO buscarPorLegajo(Integer legajo) throws Exception;

    boolean bajaUsuario(Integer id, Integer adminId) throws Exception;

    Page<Usuario> listarActivos(Pageable pageable) throws Exception;

    Page<Usuario> listarInactivos(Pageable pageable) throws Exception;

    List<UsuarioDTO> listaGerentes(Pageable pageable) throws Exception;

    List<UsuarioDTO> listaJefes(Pageable pageable) throws Exception;

    List<UsuarioDTO> listaAdministrativos(Pageable pageable) throws Exception;

    Usuario updateUser(Integer id, Usuario usuario, Integer adminId) throws Exception;

    List<UsuarioDTO> listarBusquedaUniversal(Pageable page, Usuario usuario) throws Exception;
}
