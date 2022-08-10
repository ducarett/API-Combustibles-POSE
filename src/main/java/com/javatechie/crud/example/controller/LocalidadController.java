package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.dto.LocalidadConsultaDTO;
import com.javatechie.crud.example.dto.LocalidadDTO;
import com.javatechie.crud.example.utils.complete.CompleteCamposLocalidad;
import com.javatechie.crud.example.utils.complete.CompleteCamposUsuarios;
import com.javatechie.crud.example.entity.Localidad;
import com.javatechie.crud.example.service.Impl.LocalidadServiceImpl;
import com.javatechie.crud.example.utils.mapperDto.MapperLocalidadesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localidad")
public class LocalidadController {

    @Autowired
    private LocalidadServiceImpl localidadServiceImpl;
    @Autowired
    private MapperLocalidadesDTO mapperLocalidadesDTO;
    @Autowired
    private CompleteCamposLocalidad completeCampos;

    /**
     * lista alfabeticamente todas las localidades relacionadas a una provincia
     * por ID.
     *
     * @param id
     * @return
     * @throws Exception
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/provincia/{id}")
    public ResponseEntity<?> getAllLocalidades(@PathVariable Integer id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(localidadServiceImpl.listLocalidades(id));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * crea una localidad
     *
     * @param entity
     * @return
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    public ResponseEntity<?> createLocalidad(@RequestBody Localidad entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(localidadServiceImpl.save(completeCampos.localidadCamposAlta(entity)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    /**
     * baja de localidad
     *
     * @param id
     * @return
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @DeleteMapping("/inactive/{id}")
    public ResponseEntity<?> bajaLocalidad(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(localidadServiceImpl.bajaLocalidad(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    /**
     * devuelve una localidad por ID
     *
     * @param id
     * @return
     * @throws Exception
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public Localidad getLocalidad(@PathVariable Integer id) throws Exception {
        try {
            return localidadServiceImpl.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * devuelve el nombre de una localidad por ID
     *
     * @param id
     * @return
     * @throws Exception
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/nombre/{id}")
    public String getNombreLocalidad(@PathVariable Integer id) throws Exception {
        try {
            return localidadServiceImpl.buscarPorId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * modifica una localidad.
     *
     * @param id
     * @param entity
     * @return
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLocalidad(@PathVariable int id, @RequestBody Localidad entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(localidadServiceImpl.update(id, completeCampos.localidadCamposMod(entity)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    /**
     * lista las las localidades en de forma alfabetica segun provincias.
     *
     * @return
     * @throws Exception
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/active")
    public List<LocalidadDTO> listLocalidadesActivas() throws Exception {
        try {
            return localidadServiceImpl.ordenarLocalidades();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    /**
     * lista localidades con el campo activo.
     *
     * @return
     * @throws Exception
     */
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/all")
    public List<LocalidadConsultaDTO> listLocalidadesActivasInactivas() throws Exception {
        try {
            return localidadServiceImpl.ordenarLocalidadesActivosInactivos();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
