package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.service.Impl.process.LocalidadProcessServiceImpl;
import com.javatechie.crud.example.entity.Localidad;
import com.javatechie.crud.example.service.interfaz.Busqueda;
import com.javatechie.crud.example.utils.constantes.Constant;
import com.javatechie.crud.example.utils.processDto.impl.LocalidadProcesListServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/localidad")
public class LocalidadController {
    private LocalidadProcessServiceImpl localidadProcessServiceImpl;
    private LocalidadProcesListServiceImpl localidadProcessListServiceImpl;
    private Busqueda busqueda;

    public LocalidadController(LocalidadProcessServiceImpl localidadProcessServiceImpl,
                               LocalidadProcesListServiceImpl localidadProcessListServiceImpl, Busqueda busqueda) {
        this.localidadProcessServiceImpl = localidadProcessServiceImpl;
        this.localidadProcessListServiceImpl = localidadProcessListServiceImpl;
        this.busqueda = busqueda;
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR})
    public ResponseEntity<?> createLocalidad(@RequestBody Localidad entity, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(localidadProcessServiceImpl.crear(entity, adminId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @DeleteMapping("/inactive/{id}")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR})
    public ResponseEntity<?> bajaLocalidad(@PathVariable int id, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(localidadProcessServiceImpl.baja(id, adminId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PutMapping("/update/{id}")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR})
    public ResponseEntity<?> updateLocalidad(@PathVariable int id, @RequestBody Localidad entity, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(localidadProcessServiceImpl.actualizar(id, entity, adminId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getLocalidad(@PathVariable Integer id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(localidadProcessServiceImpl.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/provincia/{id}")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getAllLocalidades(@PathVariable Integer id, Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(localidadProcessListServiceImpl.listLocalidadesProvId(id, pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/active")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> listLocalidadesActivas(Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(localidadProcessListServiceImpl.listarActivos(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/inactive")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> listLocalidadesInactivas(Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(localidadProcessListServiceImpl.listarInactivos(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/all")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> listLocalidadesCompletas(Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(localidadProcessListServiceImpl.listarTodos(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("search/nombre")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> listLocalidadesNombre(Pageable pageable, @RequestParam String nombre) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarLocalidadPorNombre(pageable, nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
