package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.service.interfaz.UsuarioFactoryService;
import com.javatechie.crud.example.utils.complete.CompleteCamposUsuarios;
import com.javatechie.crud.example.utils.constantes.Constant;
import com.javatechie.crud.example.utils.metodo.MetodosUsuariosUtils;
import com.javatechie.crud.example.utils.mapperDto.MapperUsuariosDTO;
import com.javatechie.crud.example.dto.UserConsultaDTO;
import com.javatechie.crud.example.dto.UserDTO;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.service.Impl.UsuarioServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;


import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UsuarioController {
    private static final String FAILED_PROCESS = "Error al ejecutar el proceso, ";
    private final UsuarioServiceImpl userServiceImpl;
    private final MapperUsuariosDTO mapperUsuariosDTO;
    private final CompleteCamposUsuarios completeCamposUsuarios;
    private final MetodosUsuariosUtils metodosUsuariosUtils;

    private final UsuarioFactoryService usuarioFactoryService;

    public UsuarioController(UsuarioServiceImpl userServiceImpl, MapperUsuariosDTO mapperUsuariosDTO,
                             CompleteCamposUsuarios completeCamposUsuarios, MetodosUsuariosUtils metodosUsuariosUtils,
                             UsuarioFactoryService usuarioFactoryService) {
        this.userServiceImpl = userServiceImpl;
        this.mapperUsuariosDTO = mapperUsuariosDTO;
        this.completeCamposUsuarios = completeCamposUsuarios;
        this.metodosUsuariosUtils = metodosUsuariosUtils;
        this.usuarioFactoryService = usuarioFactoryService;
    }


    /**
     * Crea User nuevo, hardcodea los campos dateAdd y hourAdd con la fecha y hora actual del sistema, encripta la password.
     *
     * @param usuario
     * @return
     */
    @PostMapping("/create")
    @RolesAllowed(Constant.ROL_ADMINISTRADOR)
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioFactoryService.crearUsuario(usuario, adminId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FAILED_PROCESS + e.getMessage());
        }
    }

    /**
     * Modifica la entidad User, antes de eso comprueba que los campos celular mail o legajo no esten repetidos.
     * Luego setea los campos fechaMod, horaMod con los datos actuales del sistema y el campo usuarioMod.
     *
     * @param id
     * @param usuario
     * @return
     */
    @PutMapping("/update/{id}")
    @RolesAllowed(Constant.ROL_ADMINISTRADOR)
    public ResponseEntity<?> UpdateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioFactoryService.actualizarUsuario(id, adminId, usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FAILED_PROCESS + e.getMessage());
        }
    }

    /**
     * Desactiva el User.
     *
     * @param id
     * @return
     */
    @DeleteMapping("/inactive/{id}")
    @RolesAllowed(Constant.ROL_ADMINISTRADOR)
    public ResponseEntity<?> bajaUsuario(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userServiceImpl.bajaUsuario(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FAILED_PROCESS);
        }
    }

    /**
     * lista los usuarios activos.
     *
     * @param pageable
     * @return
     */
    @GetMapping("/active")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getUserActivos(Pageable pageable) {
        try {
            Page<Usuario> usuarios = userServiceImpl.listarActivos(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(mapperUsuariosDTO.mapperDtoUsuarioActivo(usuarios));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FAILED_PROCESS);
        }
    }

    /**
     * Lista los usuarios inactivos.
     *
     * @param pageable
     * @return
     */
    @GetMapping("/inactive")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getUerInactivos(Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapperUsuariosDTO.mapperDtoUsuarioInactivo(userServiceImpl.listarInactivos(pageable)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\" por favor intentelo mas tarde.\"}" + e.getMessage());
        }
    }

    /**
     * retorna una lista de dtos con el campo activo en si o no.
     *
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getAll")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<UserConsultaDTO> getAllUsuarios(Pageable pageable) throws Exception {
        try {
            return mapperUsuariosDTO.mapperDtoConsultaUsuarios(userServiceImpl.findAll(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Retorna un User segun el ID.
     *
     * @param id
     * @return
     */
    // @Secured("ADMINISTRATIVO")
    @GetMapping("/")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getUsuario(@RequestParam Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FAILED_PROCESS);
        }
    }

    /**
     * busca usuarios por nombre.
     *
     * @param name
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/search/nombre")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<UserDTO> searchForNombre(@RequestParam String name, Pageable pageable) throws Exception {
        try {
            return userServiceImpl.listarPorNombre(name.toUpperCase(), pageable);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }

    /**
     * busca usuarios por apellido.
     *
     * @param lastName
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/search/apellido")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<UserDTO> searchForApellido(@RequestParam String lastName, Pageable pageable) throws Exception {
        try {
            return userServiceImpl.listarPorApellido(lastName.toUpperCase(), pageable);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }

    /**
     * busca usuarios por cargo.
     *
     * @param position
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/search/cargo")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<UserDTO> searchForCargo(@RequestParam String position, Pageable pageable) throws Exception {
        try {
            return userServiceImpl.listarPorCargo(position.toUpperCase(), pageable);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }

    /**
     * busca usuarios por username(login).
     *
     * @param userName
     * @return
     * @throws Exception
     */
    @GetMapping("/search/userName")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public UserDTO searchForLogin(@RequestParam String userName) throws Exception {
        try {
            return userServiceImpl.buscarPorLogin(userName.toUpperCase());
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }

    /**
     * busca usuarios por legajo.
     *
     * @param legajo
     * @return
     * @throws Exception
     */
    @GetMapping("/search/legajo")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public UserDTO searchForlegajo(@RequestParam Integer legajo) throws Exception {
        try {
            return userServiceImpl.buscarPorLegajo(legajo);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }

    /**
     * cambia la clave de usuario y lo persiste y lo retorna.
     *
     * @param pass
     * @param newPass
     * @param newPassConf
     * @param id
     * @return
     * @throws Exception
     */
    @PutMapping("/updatePass")
    @RolesAllowed(Constant.ROL_ADMINISTRADOR)
    public Usuario updatePassword(@RequestParam String pass, @RequestParam String newPassConf, @RequestParam Integer id, @RequestParam String newPass) throws Exception {
        try {
            return metodosUsuariosUtils.cambiarClave(pass, newPass, newPassConf, id);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage() + ". Error al actualizar password");
        }
    }

    /**
     * retorna una lista de gerentes con nombre y apellido ordenados alfabeticamente.
     *
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/gerentes")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<String> listGerentes(Pageable pageable) throws Exception {
        try {
            return userServiceImpl.listaGerentes(pageable);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }

    /**
     * retorna una lista de jefes con nombre y apellido ordenados alfabeticamente.
     *
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/jefes")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<String> listJefes(Pageable pageable) throws Exception {
        try {
            return userServiceImpl.listaJefes(pageable);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }

    /**
     * retorna una lista de administrativos rodenados alfabeticamente.
     *
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/administrativos")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<String> listAdministrativos(Pageable pageable) throws Exception {
        try {
            return userServiceImpl.listaAdministrativos(pageable);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }
}
