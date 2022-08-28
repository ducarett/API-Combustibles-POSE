package com.javatechie.crud.example.service.Impl;

import java.util.List;

import com.javatechie.crud.example.service.interfaz.UsuarioService;
import com.javatechie.crud.example.utils.complete.impl.CompleteCamposUsuarios;
import com.javatechie.crud.example.utils.metodo.MetodosUsuariosUtils;
import com.javatechie.crud.example.utils.mapperDto.MapperUsuariosDTO;
import com.javatechie.crud.example.dto.UserDTO;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javatechie.crud.example.repository.UsuarioRepository;

@Slf4j
@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Integer> implements UsuarioService {
    private UsuarioRepository usuarioRepository;
    private CompleteCamposUsuarios completeCamposUsuarios;
    private MapperUsuariosDTO mapperUsuariosDTO;
    private MetodosUsuariosUtils metodosUsuariosUtils;

    public UsuarioServiceImpl(InterfaceBaseRepository<Usuario, Integer> interfaceBaseRepository, UsuarioRepository usuarioRepository, CompleteCamposUsuarios completeCamposUsuarios,
                              MapperUsuariosDTO mapperUsuariosDTO, MetodosUsuariosUtils metodosUsuariosUtils) {
        super(interfaceBaseRepository);
        this.usuarioRepository = usuarioRepository;
        this.completeCamposUsuarios = completeCamposUsuarios;
        this.mapperUsuariosDTO = mapperUsuariosDTO;
        this.metodosUsuariosUtils = metodosUsuariosUtils;
    }

    /**
     * retorna una lista de usuarios por Nombre.
     *
     * @param nombre
     * @return
     */
    @Override
    public List<UserDTO> listarPorNombre(String nombre, Pageable pageable) throws Exception {
        try {
            return mapperUsuariosDTO.mapperDtoUsuarioActivo(usuarioRepository.findByNombre(nombre, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * retorna una lista de usuarios por apellido.
     *
     * @param apellido
     * @return
     */
    @Override
    public List<UserDTO> listarPorApellido(String apellido, Pageable pageable) throws Exception {
        try {
            return mapperUsuariosDTO.mapperDtoUsuarioActivo(usuarioRepository.findByApellido(apellido, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * retorna una lista de usuarios por cargo.
     *
     * @param cargo
     * @return
     */
    @Override
    public List<UserDTO> listarPorCargo(String cargo, Pageable pageable) throws Exception {
        try {
            return mapperUsuariosDTO.mapperDtoUsuarioActivo(usuarioRepository.findByCargo(cargo, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * retorna una lista de usuarios por legajo.
     *
     * @param legajo
     * @return
     */
    @Override
    public UserDTO buscarPorLegajo(Integer legajo) throws Exception {
        try {
            return new ModelMapper().map(usuarioRepository.findByLegajo(legajo), UserDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * retorna un usuario por Login.
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public Usuario getPorLogin(String user) throws Exception {
        try {
            return usuarioRepository.findByLogin(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * retorna un DTO de usuario por login.
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public UserDTO buscarPorLogin(String user) throws Exception {
        try {
            return mapperUsuariosDTO.buscarPorLogin(usuarioRepository.findByLogin(user));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * baja un usuario mediante el ID.
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean bajaUsuario(Integer id,Integer adminId) throws Exception {
        try {
            if (interfaceBaseRepository.existsById(id)) {
                interfaceBaseRepository.save(completeCamposUsuarios.usuarioCamposBaja(getById(id),adminId));
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * devuelve una lista de los usuarios activos.
     *
     * @return
     * @throws Exception
     */
    @Override
    public Page<Usuario> listarActivos(Pageable pageable) throws Exception {
        try {
            return usuarioRepository.findByFechaBajaIsNullAndHoraBajaIsNull(pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * devuelve una lista de usuarios inactivos.
     *
     * @return
     * @throws Exception
     */
    @Override
    public Page<Usuario> listarInactivos(Pageable pageable) throws Exception {
        try {
            return usuarioRepository.findByFechaBajaIsNotNullAndHoraBajaIsNotNull(pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * lista nombres completo de los gerentes alfabeticamente.
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<UserDTO> listaGerentes(Pageable pageable) throws Exception {
        try {
            return metodosUsuariosUtils.listarAlfabeticamenteNomApell(usuarioRepository.findListGerente(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * retorna una lista de nombre completo de jefes alfabeticamente.
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<UserDTO> listaJefes(Pageable pageable) throws Exception {
        try {
            return metodosUsuariosUtils.listarAlfabeticamenteNomApell(usuarioRepository.findListJefes(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * lista los administrativos con nombre completo alfabeticamente.
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<UserDTO> listaAdministrativos(Pageable pageable) throws Exception {
        try {
            return metodosUsuariosUtils.listarAlfabeticamenteNomApell(usuarioRepository.findListAdministrativo(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario updateUser(Integer id, Usuario usuario, Integer adminId) throws Exception {
        try {
            log.info("Verificando la existencia del usuario con id {}", id);
            if (usuarioRepository.existsById(id)) {
                log.info("el usuario {} con id {}, existe!", usuario.getApellido().concat(" " + usuario.getNombre()), id);
                Usuario usuarioactualizado = getById(id);
                return save(completeCamposUsuarios.setDatosModificados(usuarioactualizado, usuario,adminId));
            } else {
                throw new Exception("El usuario no existe");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}