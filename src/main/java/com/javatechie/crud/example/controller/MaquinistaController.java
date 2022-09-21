package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.service.Impl.process.MaquinistaProcessServiceImpl;
import com.javatechie.crud.example.entity.Maquinista;
import com.javatechie.crud.example.service.interfaz.Busqueda;
import com.javatechie.crud.example.utils.constantes.Constant;
import com.javatechie.crud.example.utils.processDto.impl.MaquinistaProcessListServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RequestMapping("/maquinista")
@RestController
public class MaquinistaController {
    private MaquinistaProcessServiceImpl maquinistaProcessService;
    private Busqueda busqueda;
    private MaquinistaProcessListServiceImpl maquinistaProcessDtoService;

    public MaquinistaController(MaquinistaProcessServiceImpl maquinistaProcessService,
                                Busqueda busqueda, MaquinistaProcessListServiceImpl maquinistaProcessDtoService) {
        this.maquinistaProcessService = maquinistaProcessService;
        this.busqueda = busqueda;
        this.maquinistaProcessDtoService = maquinistaProcessDtoService;
    }


    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR})
    public ResponseEntity<?> crearMaquinista(@RequestBody Maquinista entity, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaProcessService.crear(entity, adminId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PutMapping("/update/{id}")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR})
    public ResponseEntity<?> modificarMaquinista(@PathVariable int id, @RequestBody Maquinista entity, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaProcessService.actualizar(id, entity, adminId));
        } catch (
                Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @DeleteMapping("/inactive/{id}")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR})
    public ResponseEntity<?> bajaMaquinista(@PathVariable int id, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(maquinistaProcessService.baja(id, adminId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getMaquinista(@RequestParam Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaProcessService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/active")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getUsuariosActivos(Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaProcessDtoService.listarActivos(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/nombre")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getMaquinistaNombre(Pageable pageable, @RequestParam String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarMaquinistaPorNombre(pageable, nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/apellido")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getMaquinistaApellido(Pageable pageable, @RequestParam String apellido) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarMaquinistaPorApellido(pageable, apellido));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/legajo")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getMaquinistaLegajo(@RequestParam Integer legajo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarMaquinistaPorLegajo(legajo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/getAll")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getAllMaquinistas(Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaProcessDtoService.listarTodos(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
