package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.utils.complete.CompleteCamposObras;
import com.javatechie.crud.example.dto.ObrasActivasDTO;
import com.javatechie.crud.example.entity.Obra;
import com.javatechie.crud.example.service.Impl.ObraServiceImpl;
import com.javatechie.crud.example.utils.constantes.Constant;
import com.javatechie.crud.example.utils.mapperDto.MapperObrasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/obras")
public class ObraController {

    @Autowired
    private ObraServiceImpl obraServiceImpl;

    @Autowired
    private MapperObrasDTO mapperObrasDTO;

    @Autowired
    private CompleteCamposObras completeCamposObras;

    /**
     * Crea Obra nueva, hardcodea los campos dateAdd y hourAdd con la fecha y hora actual del sistema, encripta la password.
     *
     * @param entity
     * @return
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR})
    public ResponseEntity<?> createObra(@RequestBody Obra entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(obraServiceImpl.crearObra(completeCamposObras.obraCamposAlta(entity)));
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
    public List<ObrasActivasDTO> getAllObrasActivas(Pageable pageable) throws Exception {
        try {
            return mapperObrasDTO.mapperDtoObrasActivas(obraServiceImpl.listActivas(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
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
    public List<ObrasActivasDTO> getAllObrasInactivas(Pageable pageable) throws Exception {
        try {
            return mapperObrasDTO.mapperDtoObrasActivas(obraServiceImpl.listInactivas(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
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
    public Obra searchForObraID(@PathVariable Integer id) throws Exception {
        try {
            return obraServiceImpl.getById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
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
    public List<ObrasActivasDTO> searchForDescription(@RequestParam String descripcion, Pageable pageable) throws Exception {
        try {
            return obraServiceImpl.listDescripcion(descripcion.toUpperCase(), pageable);
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
    public List<ObrasActivasDTO> searchForProvince(@RequestParam String provincia, Pageable pageable) throws Exception {
        try {
            return obraServiceImpl.listProvincia(provincia.toUpperCase(), pageable);
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
    public List<ObrasActivasDTO> searchForLocalidad(@RequestParam String localidad, Pageable pageable) throws Exception {
        try {
            return obraServiceImpl.listLocalidad(localidad.toUpperCase(), pageable);
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
    public List<ObrasActivasDTO> searchForGerente(@RequestParam String gerente, Pageable pageable) throws Exception {
        try {
            return obraServiceImpl.listGerente(gerente.toUpperCase(), pageable);
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
    public List<ObrasActivasDTO> searchForJefe(@RequestParam String jefe, Pageable pageable) throws Exception {
        try {
            return obraServiceImpl.listJefe(jefe.toUpperCase(), pageable);
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
    public List<ObrasActivasDTO> searchForAdministrativo(@RequestParam String administrativo, Pageable pageable) throws Exception {
        try {
            return obraServiceImpl.listAdministrativo(administrativo.toUpperCase(), pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
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
    public ResponseEntity<?> bajaObra(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(obraServiceImpl.bajaObra(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
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
    public ResponseEntity<?> updateObra(@PathVariable int id, @RequestBody Obra entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(obraServiceImpl.update(id, completeCamposObras.obraCamposMod(entity)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }
}
