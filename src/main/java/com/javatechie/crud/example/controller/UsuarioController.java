package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.service.interfaz.UsuarioFactoryService;
import com.javatechie.crud.example.utils.complete.impl.CompleteCamposUsuarios;
import com.javatechie.crud.example.utils.constantes.Constant;
import com.javatechie.crud.example.utils.metodo.MetodosUsuariosUtils;
import com.javatechie.crud.example.utils.mapperDto.MapperUsuariosDTO;
import com.javatechie.crud.example.dto.UserConsultaDTO;
import com.javatechie.crud.example.dto.UsuarioDTO;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.service.Impl.UsuarioServiceImpl;
import com.javatechie.crud.example.utils.processDto.impl.UsuarioProcessListServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UsuarioController {
    private static final String FAILED_PROCESS = "Error al ejecutar el proceso, ";
    public static final String SEARCH_NO_FOUND = "No se encontraron resultados que coincidan con los filtros de busqueda!";
    private final UsuarioServiceImpl userServiceImpl;
    private final MapperUsuariosDTO mapperUsuariosDTO;
    private final CompleteCamposUsuarios completeCamposUsuarios;
    private final MetodosUsuariosUtils metodosUsuariosUtils;

    private final UsuarioProcessListServiceImpl usuarioProcessListService;
    private final UsuarioFactoryService usuarioFactoryService;

    public UsuarioController(UsuarioServiceImpl userServiceImpl, MapperUsuariosDTO mapperUsuariosDTO,
                             CompleteCamposUsuarios completeCamposUsuarios, MetodosUsuariosUtils metodosUsuariosUtils,
                             UsuarioProcessListServiceImpl usuarioProcessListService, UsuarioFactoryService usuarioFactoryService) {
        this.userServiceImpl = userServiceImpl;
        this.mapperUsuariosDTO = mapperUsuariosDTO;
        this.completeCamposUsuarios = completeCamposUsuarios;
        this.metodosUsuariosUtils = metodosUsuariosUtils;
        this.usuarioProcessListService = usuarioProcessListService;
        this.usuarioFactoryService = usuarioFactoryService;
    }


    @PostMapping("/create")
    @RolesAllowed(Constant.ROL_ADMINISTRADOR)
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioFactoryService.crearUsuario(usuario, adminId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FAILED_PROCESS + e.getMessage());
        }
    }


    @PutMapping("/update/{id}")
    @RolesAllowed(Constant.ROL_ADMINISTRADOR)
    public ResponseEntity<?> UpdateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioFactoryService.actualizarUsuario(id, adminId, usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FAILED_PROCESS + e.getMessage());
        }
    }


    @DeleteMapping("/inactive/{id}")
    @RolesAllowed(Constant.ROL_ADMINISTRADOR)
    public ResponseEntity<?> bajaUsuario(@PathVariable int id, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userServiceImpl.bajaUsuario(id, adminId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FAILED_PROCESS);
        }
    }


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


    @GetMapping("/inactive")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getUerInactivos(Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapperUsuariosDTO.mapperDtoUsuarioInactivo(userServiceImpl.listarInactivos(pageable)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\" por favor intentelo mas tarde.\"}" + e.getMessage());
        }
    }


    @GetMapping("/getAll")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<UserConsultaDTO> getAllUsuarios(Pageable pageable) throws Exception {
        try {
            return mapperUsuariosDTO.mapperDtoConsultaUsuarios(userServiceImpl.findAll(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @GetMapping("/")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getUsuario(@RequestParam Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FAILED_PROCESS);
        }
    }


    @GetMapping("/search/nombre")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<UsuarioDTO> searchForNombre(@RequestParam String name, Pageable pageable) throws Exception {
        try {
            return userServiceImpl.listarPorNombre(name.toUpperCase(), pageable);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }


    @GetMapping("/search/apellido")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<UsuarioDTO> searchForApellido(@RequestParam String lastName, Pageable pageable) throws Exception {
        try {
            return userServiceImpl.listarPorApellido(lastName.toUpperCase(), pageable);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }


    @GetMapping("/search/cargo")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<UsuarioDTO> searchForCargo(@RequestParam String position, Pageable pageable) throws Exception {
        try {
            return userServiceImpl.listarPorCargo(position.toUpperCase(), pageable);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }


    @GetMapping("/search/userName")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public UsuarioDTO searchForLogin(@RequestParam String userName) throws Exception {
        try {
            return userServiceImpl.buscarPorLogin(userName.toUpperCase());
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }


    @GetMapping("/search/legajo")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public UsuarioDTO searchForlegajo(@RequestParam Integer legajo) throws Exception {
        try {
            return userServiceImpl.buscarPorLegajo(legajo);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }


    @PutMapping("/updatePass")
    @RolesAllowed(Constant.ROL_ADMINISTRADOR)
    public Usuario updatePassword(@RequestParam String pass, @RequestParam String newPassConf, @RequestParam Integer id, @RequestParam String newPass) throws Exception {
        try {
            return metodosUsuariosUtils.cambiarClave(pass, newPass, newPassConf, id);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage() + ". Error al actualizar password");
        }
    }


    @GetMapping("/gerentes")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<UsuarioDTO> listGerentes(Pageable pageable) throws Exception {
        try {
            return userServiceImpl.listaGerentes(pageable);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }


    @GetMapping("/jefes")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<UsuarioDTO> listJefes(Pageable pageable) throws Exception {
        try {
            return userServiceImpl.listaJefes(pageable);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }


    @GetMapping("/administrativos")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<UsuarioDTO> listAdministrativos(Pageable pageable) throws Exception {
        try {
            return userServiceImpl.listaAdministrativos(pageable);
        } catch (Exception e) {
            throw new Exception(FAILED_PROCESS + e.getMessage());
        }
    }

    @GetMapping("/search")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> busquedaUniversal(Pageable pageable,@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioProcessListService.listarBusqueda(pageable, usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SEARCH_NO_FOUND);
        }
    }
}
