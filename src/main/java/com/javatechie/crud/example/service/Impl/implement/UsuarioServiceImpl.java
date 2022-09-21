package com.javatechie.crud.example.service.Impl.implement;

import java.util.Objects;

import com.javatechie.crud.example.service.Impl.abstractClass.BaseServiceImpl;
import com.javatechie.crud.example.service.interfaz.Encriptacion;
import com.javatechie.crud.example.service.interfaz.UsuarioService;
import com.javatechie.crud.example.utils.complete.impl.CompleteCamposUsuarios;
import com.javatechie.crud.example.utils.metodo.MetodosUsuariosUtils;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javatechie.crud.example.repository.UsuarioRepository;

@Slf4j
@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Integer> implements UsuarioService {
    private static final String NO_EXIST = "El usuario no existe en base!";
    private UsuarioRepository usuarioRepository;
    private CompleteCamposUsuarios completeCamposUsuarios;
    private MetodosUsuariosUtils metodosUsuariosUtils;
    private Encriptacion encriptacion;

    public UsuarioServiceImpl(InterfaceBaseRepository<Usuario, Integer> interfaceBaseRepository, UsuarioRepository usuarioRepository, CompleteCamposUsuarios completeCamposUsuarios,
                              MetodosUsuariosUtils metodosUsuariosUtils, Encriptacion encriptacion) {
        super(interfaceBaseRepository);
        this.usuarioRepository = usuarioRepository;
        this.completeCamposUsuarios = completeCamposUsuarios;
        this.metodosUsuariosUtils = metodosUsuariosUtils;
        this.encriptacion = encriptacion;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario, Integer adminId) throws Exception {
        usuario.setLogin(metodosUsuariosUtils.crearUserName(usuario.getNombre(), usuario.getApellido()));
        usuario.setPassword(encriptacion.encriptarClave(usuario.getPassword().toUpperCase()));
        return save(completeCamposUsuarios.verificarDatosEnBase(usuario) ?
        completeCamposUsuarios.usuarioCamposAlta(usuario, adminId) : new Usuario());
    }

    @Override
    public Usuario updateUser(Integer id, Usuario usuario, Integer adminId) throws Exception {
        try {
            if (completeCamposUsuarios.verificarIgualdadEnBase(usuario, id))
                usuario.setLogin((completeCamposUsuarios.verificarUsername(usuario, id)) ? metodosUsuariosUtils.crearUserName(usuario.getNombre(), usuario.getApellido()) : usuario.getLogin());
            log.info("Verificando la existencia del usuario con id {}", id);
            if (usuarioRepository.existsById(id)) {
                log.info("el usuario {} con id {}, existe!", usuario.getApellido().concat(" " + usuario.getNombre()), id);
                Usuario usuarioactualizado = getById(id);
                return save(completeCamposUsuarios.setDatosModificados(usuarioactualizado, usuario, adminId));
            } else {
                throw new Exception("El usuario no existe");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean bajaUsuario(Integer id, Integer adminId) throws Exception {

        try {
            Usuario usuario = getById(id);
            if (Objects.isNull(usuario)) throw new Exception(NO_EXIST);
            completeCamposUsuarios.usuarioCamposBaja(usuario, adminId);
            update(id, usuario);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario getUsuario(Integer id) throws Exception {
        try {
            return getEntity(getById(id));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Usuario> listarPorNombre(String nombre, Pageable pageable) throws Exception {
        try {
            return listGeneric(usuarioRepository.findListByNombre(nombre, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Usuario> listarPorApellido(String apellido, Pageable pageable) throws Exception {
        try {
            return listGeneric(usuarioRepository.findListByApellido(apellido, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Usuario> listarPorCargo(String cargo, Pageable pageable) throws Exception {
        try {
            return listGeneric(usuarioRepository.findListByCargo(cargo, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario buscarPorLegajo(Integer legajo) throws Exception {
        try {
            return getEntity(usuarioRepository.findByLegajo(legajo));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario getPorLogin(String user) throws Exception {
        try {
            return getEntity(usuarioRepository.findByLogin(user));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public Page<Usuario> listarActivos(Pageable pageable) throws Exception {
        try {
            return listGeneric(usuarioRepository.findByFechaBajaIsNullAndHoraBajaIsNull(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Usuario> listarInactivos(Pageable pageable) throws Exception {
        try {
            return listGeneric(usuarioRepository.findByFechaBajaIsNotNullAndHoraBajaIsNotNull(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Usuario> listAllUsuarios(Pageable pageable) throws Exception {
        try {
            return listGeneric(usuarioRepository.findAll(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Usuario> listaGerentes(Pageable pageable) throws Exception {
        try {
            return listGeneric(usuarioRepository.findListGerente(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Usuario> listaJefes(Pageable pageable) throws Exception {
        try {
            return listGeneric(usuarioRepository.findListJefes(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Usuario> listaAdministrativos(Pageable pageable) throws Exception {
        try {
            return listGeneric(usuarioRepository.findListAdministrativo(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Usuario> listarBusquedaUniversal(Pageable page, Usuario usuario) throws Exception {
        try {
            return listGeneric(usuarioRepository.findListBuscados(page, usuario.getNombre(), usuario.getApellido(), usuario.getLogin(),
                    usuario.getLegajo(), usuario.getCargo().getDescripcionCargo()));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}