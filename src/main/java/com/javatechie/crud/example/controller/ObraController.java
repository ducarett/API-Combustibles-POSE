package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.service.Impl.ObraProcessServiceImpl;
import com.javatechie.crud.example.dto.ObraDTO;
import com.javatechie.crud.example.entity.Obra;
import com.javatechie.crud.example.utils.constantes.Constant;
import com.javatechie.crud.example.utils.processDto.impl.ObraProcessDtoServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/obras")
public class ObraController {

    private ObraProcessServiceImpl obraProcessServiceImpl;

    private ObraProcessDtoServiceImpl obraProcessDtoService;

    public ObraController(ObraProcessServiceImpl obraProcessServiceImpl,
                          ObraProcessDtoServiceImpl obraProcessDtoService) {
        this.obraProcessServiceImpl = obraProcessServiceImpl;
        this.obraProcessDtoService = obraProcessDtoService;
    }

    /**
     * Crea Obra nueva, hardcodea los campos dateAdd y hourAdd con la fecha y hora actual del sistema, encripta la password.
     *
     * @param entity
     * @return
     */
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

    /**
     * modifica una obra.
     *
     * @param id
     * @param entity
     * @return
     */
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

    /**
     * da de baja una obra.
     *
     * @param id
     * @return
     */
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


    /**
     * retorna una lista de obras activas.
     *
     * @param pageable
     * @return
     * @throws Exception
     */
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

    /**
     * retorna una lista de obras inactivas.
     *
     * @param pageable
     * @return
     * @throws Exception
     */
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

    /**
     * busca obras por ID.
     *
     * @param id
     * @return
     * @throws Exception
     */
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

    /**
     * busca obras por descripcion.
     *
     * @param descripcion
     * @param pageable
     * @return
     * @throws Exception
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/search/descripcion")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<ObraDTO> searchForDescription(@RequestParam String descripcion, Pageable pageable) throws Exception {
        try {
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca obras por provincia.
     *
     * @param provincia
     * @param pageable
     * @return
     * @throws Exception
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/search/provincia")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<ObraDTO> searchForProvince(@RequestParam String provincia, Pageable pageable) throws Exception {
        try {
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca obras por localidad.
     *
     * @param localidad
     * @param pageable
     * @return
     * @throws Exception
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/search/localidad")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<ObraDTO> searchForLocalidad(@RequestParam String localidad, Pageable pageable) throws Exception {
        try {
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca obras por puesto de trabajo : gerente.
     *
     * @param gerente
     * @param pageable
     * @return
     * @throws Exception
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/search/gerente")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<ObraDTO> searchForGerente(@RequestParam String gerente, Pageable pageable) throws Exception {
        try {
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca obras por puesto de trabajo : jefe
     *
     * @param jefe
     * @param pageable
     * @return
     * @throws Exception
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/search/jefe")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<ObraDTO> searchForJefe(@RequestParam String jefe, Pageable pageable) throws Exception {
        try {
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca obras por puesto de trabajo : administrativo.
     *
     * @param administrativo
     * @param pageable
     * @return
     * @throws Exception
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/search/administrativo")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<ObraDTO> searchForAdministrativo(@RequestParam String administrativo, Pageable pageable) throws Exception {
        try {
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
