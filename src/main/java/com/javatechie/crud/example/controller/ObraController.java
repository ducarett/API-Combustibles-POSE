package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.service.Impl.process.ObraProcessServiceImpl;
import com.javatechie.crud.example.entity.Obra;
import com.javatechie.crud.example.service.interfaz.Busqueda;
import com.javatechie.crud.example.utils.constantes.Constant;
import com.javatechie.crud.example.utils.processDto.impl.ObraProcessListDtoServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/obras")
public class ObraController {

    private ObraProcessServiceImpl obraProcessServiceImpl;

    private ObraProcessListDtoServiceImpl obraProcessDtoService;

    private Busqueda busqueda;

    public ObraController(ObraProcessServiceImpl obraProcessServiceImpl,
                          ObraProcessListDtoServiceImpl obraProcessDtoService, Busqueda busqueda) {
        this.obraProcessServiceImpl = obraProcessServiceImpl;
        this.obraProcessDtoService = obraProcessDtoService;
        this.busqueda = busqueda;
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR})
    public ResponseEntity<?> createObra(@RequestBody Obra entity, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(obraProcessServiceImpl.crear(entity, adminId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PutMapping("/update/{id}")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR})
    public ResponseEntity<?> updateObra(@PathVariable int id, @RequestBody Obra entity, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(obraProcessServiceImpl.actualizar(id, entity, adminId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @DeleteMapping("/inactive/{id}")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR})
    public ResponseEntity<?> bajaObra(@PathVariable int id, @RequestHeader Integer adminId) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(obraProcessServiceImpl.baja(id, adminId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/activas")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getAllObrasActivas(Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(obraProcessDtoService.listarActivos(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/inactivas")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getAllObrasInactivas(Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(obraProcessDtoService.listarActivos(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/search/{id}")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> searchForObraID(@PathVariable Integer id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(obraProcessServiceImpl.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/search/descripcion")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> searchForDescription(@RequestParam String descripcion, Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarObraPorDescripcion(pageable, descripcion));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/search/provincia")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> searchForProvince(@RequestParam String provincia, Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarObraPorProvincia(pageable, provincia));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/search/localidad")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> searchForLocalidad(@RequestParam String localidad, Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarObraPorLocalidad(pageable, localidad));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/search/gerente")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> searchForGerente(@RequestParam String gerente, Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarObraPorGerente(pageable, gerente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/search/jefe")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> searchForJefe(@RequestParam String jefe, Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarObraPorJefe(pageable, jefe));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/search/administrativo")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> searchForAdministrativo(@RequestParam String administrativo, Pageable pageable) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(busqueda.buscarObraPorAdministrativo(pageable, administrativo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
