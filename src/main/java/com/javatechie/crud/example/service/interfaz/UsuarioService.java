package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.dto.UsuarioDTO;
import com.javatechie.crud.example.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface UsuarioService extends BaseService<Usuario, Integer> {
    Page<Usuario> buscarPorLegajo(Pageable pageable, Integer legajo) throws Exception;

    Page<Usuario> listAllUsuarios(Pageable pageable) throws Exception;

    Usuario crearUsuario(Usuario usuario, Integer adminId) throws Exception;

    Usuario getPorLogin(String login) throws Exception;

    Page<Usuario> getListPorLogin(Pageable pageable, String login) throws Exception;

    Usuario getUsuario(Integer id) throws Exception;

    Page<Usuario> listarPorNombre(String nombre, Pageable pageable) throws Exception;

    Page<Usuario> listarPorApellido(String apellido, Pageable pageable) throws Exception;

    Page<Usuario> listarPorCargo(String cargo, Pageable pageable) throws Exception;

    boolean bajaUsuario(Integer id, Integer adminId) throws Exception;

    Page<Usuario> listarActivos(Pageable pageable) throws Exception;

    Page<Usuario> listarInactivos(Pageable pageable) throws Exception;

    Page<Usuario> listaGerentes(Pageable pageable) throws Exception;

    Page<Usuario> listaJefes(Pageable pageable) throws Exception;

    Page<Usuario> listaAdministrativos(Pageable pageable) throws Exception;

    Usuario updateUser(Integer id, Usuario usuario, Integer adminId) throws Exception;

    Page<Usuario> listarBusquedaUniversal(Pageable page, Usuario usuario) throws Exception;
}
