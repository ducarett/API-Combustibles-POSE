package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.service.Impl.process.UsuarioProcessServiceImpl;
import com.javatechie.crud.example.service.interfaz.Busqueda;
import com.javatechie.crud.example.service.interfaz.UsuarioFactoryService;
import com.javatechie.crud.example.utils.constantes.Constant;
import com.javatechie.crud.example.dto.UserConsultaDTO;
import com.javatechie.crud.example.dto.UsuarioDTO;
import com.javatechie.crud.example.entity.Usuario;
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

    private final Busqueda busqueda;
    private final UsuarioProcessListServiceImpl usuarioProcessListService;

    private final UsuarioProcessServiceImpl usuarioProcessService;

    public UsuarioController(Busqueda busqueda, UsuarioProcessListServiceImpl usuarioProcessListService,
                             UsuarioProcessServiceImpl usuarioProcessService) {
        this.busqueda = busqueda;
        this.usuarioProcessListService = usuarioProcessListService;
        this.usuarioProcessService = usuarioProcessService;
    }

    @PostMapping("/create")
    @RolesAllowed(Constant.ROL_ADMINISTRADOR)
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioProcessService.crear(usuario, adminId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FAILED_PROCESS + e.getMessage());
        }
    }


    @PutMapping("/update/{id}")
    @RolesAllowed(Constant.ROL_ADMINISTRADOR)
    public ResponseEntity<?> UpdateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioProcessService.actualizar(id, usuario, adminId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FAILED_PROCESS + e.getMessage());
        }
    }


    @DeleteMapping("/inactive/{id}")
    @RolesAllowed(Constant.ROL_ADMINISTRADOR)
    public ResponseEntity<?> bajaUsuario(@PathVariable int id, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuarioProcessService.baja(id, adminId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FAILED_PROCESS);
        }
    }

    @GetMapping("/")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getUsuario(@RequestParam Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioProcessService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FAILED_PROCESS);
        }
    }

    @GetMapping("/active")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getUserActivos(Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioProcessListService.listarActivos(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/inactive")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getUserInactivos(Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioProcessListService.listarInactivos(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/getAll")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getAllUsuarios(Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioProcessListService.listarTodos(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/search/nombre")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> searchForNombre(@RequestParam String name, Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarPorNombre(pageable, name));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/search/apellido")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> searchForApellido(@RequestParam String lastName, Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarPorApellido(pageable, lastName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/search/cargo")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> searchForCargo(@RequestParam String position, Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarPorCargo(pageable, position));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search/userName")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> searchForLogin(@RequestParam String userName) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarPorUserName(userName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/search/legajo")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> searchForlegajo(@RequestParam Integer legajo) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarPorLegajo(legajo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/gerentes")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> listGerentes(Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioProcessListService.listaGerentes(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/jefes")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> listJefes(Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioProcessListService.listaJefes(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/administrativos")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> listAdministrativos(Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioProcessListService.listaAdministrativos(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> busquedaUniversal(Pageable pageable, @RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.ListarBusquedaUniversal(pageable, usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
