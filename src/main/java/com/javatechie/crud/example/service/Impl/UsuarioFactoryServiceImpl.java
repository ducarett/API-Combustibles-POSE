package com.javatechie.crud.example.service.Impl;

import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.service.interfaz.Encriptacion;
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

    private Encriptacion encriptacion;

    public UsuarioFactoryServiceImpl(UsuarioService userService, MapperUsuariosDTO mapperUsuariosDTO,
                                     CompleteCamposUsuarios completeCamposUsuarios, MetodosUsuariosUtils metodosUsuariosUtils,Encriptacion encriptacion) {
        this.userService = userService;
        this.mapperUsuariosDTO = mapperUsuariosDTO;
        this.completeCamposUsuarios = completeCamposUsuarios;
        this.metodosUsuariosUtils = metodosUsuariosUtils;
        this.encriptacion = encriptacion;
    }

    /**
     * servicio para editar usuario verificando cada campo
     *
     * @param id
     * @param usuario
     * @return
     * @throws Exception
     */
    @Override
    public Usuario actualizarUsuario(Integer id, Integer adminId, Usuario usuario) throws Exception {
        try {
            if (completeCamposUsuarios.verificarIgualdadEnBase(usuario, id))
                usuario.setLogin( (completeCamposUsuarios.verificarUsername(usuario, id)) ?
                         metodosUsuariosUtils.crearUserName(usuario.getNombre(), usuario.getApellido()):
                        usuario.getLogin());
            return userService.updateUser(id, usuario,adminId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Servicio para crear usuario nuevo
     *
     * @param usuario
     * @param adminId
     * @return
     */
    public Usuario crearUsuario(Usuario usuario, Integer adminId) throws Exception {

        usuario.setLogin(metodosUsuariosUtils.crearUserName(usuario.getNombre(), usuario.getApellido()));
        usuario.setPassword(encriptacion.encriptarClave(usuario.getPassword().toUpperCase()));
        completeCamposUsuarios.verificarDatosEnBase(usuario);
        completeCamposUsuarios.usuarioCamposAlta(usuario, adminId);
        return userService.save(usuario);
    }
}
