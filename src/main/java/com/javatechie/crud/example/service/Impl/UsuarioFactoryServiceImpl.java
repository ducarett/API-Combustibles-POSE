package com.javatechie.crud.example.service.Impl;

import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.service.interfaz.UsuarioFactoryService;
import com.javatechie.crud.example.service.interfaz.UsuarioService;
import com.javatechie.crud.example.utils.complete.CompleteCamposUsuarios;
import com.javatechie.crud.example.utils.mapperDto.MapperUsuariosDTO;
import com.javatechie.crud.example.utils.metodo.MetodosUsuariosUtils;
import org.springframework.stereotype.Service;

@Service
public class UsuarioFactoryServiceImpl implements UsuarioFactoryService {

    private final UsuarioService userService;
    private final MapperUsuariosDTO mapperUsuariosDTO;
    private final CompleteCamposUsuarios completeCamposUsuarios;
    private final MetodosUsuariosUtils metodosUsuariosUtils;

    public UsuarioFactoryServiceImpl(UsuarioService userService, MapperUsuariosDTO mapperUsuariosDTO,
                                     CompleteCamposUsuarios completeCamposUsuarios, MetodosUsuariosUtils metodosUsuariosUtils) {
        this.userService = userService;
        this.mapperUsuariosDTO = mapperUsuariosDTO;
        this.completeCamposUsuarios = completeCamposUsuarios;
        this.metodosUsuariosUtils = metodosUsuariosUtils;
    }

    @Override
    public Usuario actualizarUsuario(Integer id, Usuario usuario) throws Exception {
        try {
            verificarDatosEnBase(usuario) ;
            usuario.setLogin(metodosUsuariosUtils.crearUserName(usuario.getNombre(), usuario.getApellido()));
            return userService.updateUser(id, usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean verificarDatosEnBase(Usuario usuario) throws Exception {
        try {
            if (completeCamposUsuarios.comprobarLegajo(usuario.getLegajo())) {
                throw new Exception("Este legajo ya existe!");
            }
            if (completeCamposUsuarios.comprobarCelular(usuario.getCelular())) {
                throw new Exception("Este celular ya existe!");
            }
            if (completeCamposUsuarios.comprobarMail(usuario.getMail())) {
                throw new Exception("Este mail ya existe!");
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean verificarIgualdadEnBase(Usuario usuario) throws Exception {
        try {
            if (completeCamposUsuarios.comprobarLegajo(usuario.getLegajo())) {
                throw new Exception("Este legajo ya existe!");
            }
            if (completeCamposUsuarios.comprobarCelular(usuario.getCelular())) {
                throw new Exception("Este celular ya existe!");
            }
            if (completeCamposUsuarios.comprobarMail(usuario.getMail())) {
                throw new Exception("Este mail ya existe!");
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
