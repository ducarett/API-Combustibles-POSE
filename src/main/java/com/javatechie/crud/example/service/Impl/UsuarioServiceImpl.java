package com.javatechie.crud.example.service.Impl;

import java.util.List;

import com.javatechie.crud.example.service.interfaz.UsuarioService;
import com.javatechie.crud.example.utils.complete.CompleteCamposUsuarios;
import com.javatechie.crud.example.utils.metodo.MetodosUsuariosUtils;
import com.javatechie.crud.example.utils.mapperDto.MapperUsuariosDTO;
import com.javatechie.crud.example.dto.UserDTO;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javatechie.crud.example.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Integer> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CompleteCamposUsuarios completeCamposUsuarios;
    @Autowired
    private MapperUsuariosDTO mapperUsuariosDTO;
    @Autowired
    private MetodosUsuariosUtils metodosUsuariosUtils;

    public UsuarioServiceImpl(InterfaceBaseRepository<Usuario, Integer> interfaceBaseRepository) {
        super(interfaceBaseRepository);
    }

    /**
     * retorna una lista de usuarios por Nombre.
     *
     * @param nombre
     * @return
     */
    @Override
    public List<UserDTO> listarPorNombre(String nombre,Pageable pageable) throws Exception {
        try {
            return mapperUsuariosDTO.mapperDtoUsuarioActivo(usuarioRepository.findByNombre(nombre,pageable));
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
            return mapperUsuariosDTO.mapperDtoUsuarioActivo(usuarioRepository.findByApellido(apellido,pageable));
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
    public List<UserDTO> listarPorCargo(String cargo,Pageable pageable) throws Exception {
        try {
            return mapperUsuariosDTO.mapperDtoUsuarioActivo(usuarioRepository.findByCargo(cargo,pageable));
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
            return new ModelMapper().map(usuarioRepository.findByLegajo(legajo),UserDTO.class);
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
    public boolean bajaUsuario(Integer id) throws Exception {
        try {
            if (interfaceBaseRepository.existsById(id)) {
                interfaceBaseRepository.save(completeCamposUsuarios.usuarioCamposBaja(findById(id)));
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * construye el UserName(login) con la primer letra del nombre y concatena el apellido.
     *
     * @param nombre
     * @param apellido
     * @return
     * @throws Exception
     */
    public String crearUserName(String nombre, String apellido) throws Exception {
        try {
            nombre = nombre.substring(0, 1);
            return nombre.concat(apellido).toUpperCase();
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
    public List<String> listaGerentes(Pageable pageable) throws Exception {
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
    public List<String> listaJefes(Pageable pageable) throws Exception {
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
    public List<String> listaAdministrativos(Pageable pageable) throws Exception {
        try {
            return metodosUsuariosUtils.listarAlfabeticamenteNomApell(usuarioRepository.findListAdministrativo(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
