package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.service.Impl.MaquinistaProcessServiceImpl;
import com.javatechie.crud.example.entity.Maquinista;
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

    private MaquinistaProcessListServiceImpl maquinistaProcessDtoService;

    public MaquinistaController(MaquinistaProcessServiceImpl maquinistaProcessService,
                                MaquinistaProcessListServiceImpl maquinistaProcessDtoService) {
        this.maquinistaProcessService = maquinistaProcessService;
        this.maquinistaProcessDtoService = maquinistaProcessDtoService;
    }


    /**
     * crea un maquinista verificando que el legajo no se encuentre em la base de datos.
     *
     * @param entity
     * @param adminId
     * @return
     */
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

    /**
     * Modifica la entidad User, antes de eso comprueba que los campos celular mail o legajo no esten repetidos.
     * Luego setea los campos fechaMod, horaMod con los datos actuales del sistema y el campo usuarioMod.
     *
     * @param id
     * @param entity
     * @return
     */
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

    /**
     * Desactiva el User.
     *
     * @param id
     * @return
     */
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

    /**
     * Retorna un maquinista segun el ID.
     *
     * @param id
     * @return
     */
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
    public ResponseEntity<?> getMaquinistaNombre(@RequestParam String nombre) {
        try {
            return null;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/apellido")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getMaquinistaApellido(@RequestParam String apellido) {
        try {
            return null;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/legajo")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getMaquinistaLegajo(@RequestParam Integer id) {
        try {
            return null;
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
