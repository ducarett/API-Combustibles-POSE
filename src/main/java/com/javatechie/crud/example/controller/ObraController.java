package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.utils.complete.CompleteCamposObras;
import com.javatechie.crud.example.dto.ObrasActivasDTO;
import com.javatechie.crud.example.entity.Obra;
import com.javatechie.crud.example.service.Impl.ObraServiceImpl;
import com.javatechie.crud.example.utils.mapperDto.MapperObrasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    // @Secured(("ADMINISTRADOR"))
    @PostMapping("/create")
    public ResponseEntity<?> createObra(@RequestBody Obra entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(obraServiceImpl.save(completeCamposObras.obraCamposAlta(entity)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    /**
     * retorna una lista de obras activas.
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/activas")
    public List<ObrasActivasDTO> getAllObrasActivas() throws Exception {
        try {
            return mapperObrasDTO.mapperDtoObrasActivas(obraServiceImpl.listActivas());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * retorna una lista de obras inactivas.
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/inactivas")
    public List<ObrasActivasDTO> getAllObrasInactivas() throws Exception {
        try {
            return mapperObrasDTO.mapperDtoObrasActivas(obraServiceImpl.listInactivas());
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
    @GetMapping("/search/{id}")
    public Obra searchForObraID(@PathVariable Integer id) throws Exception {
        try {
            return obraServiceImpl.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca obras por descripcion.
     *
     * @param descripcion
     * @return
     * @throws Exception
     */
    @GetMapping("/search/descripcion")
    public List<ObrasActivasDTO> searchForDescription(@RequestParam String descripcion) throws Exception {
        try {
            return obraServiceImpl.listDescripcion(descripcion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca obras por provincia.
     *
     * @param provincia
     * @return
     * @throws Exception
     */
    @GetMapping("/search/provincia")
    public List<ObrasActivasDTO> searchForProvince(@RequestParam String provincia) throws Exception {
        try {
            return obraServiceImpl.listProvincia(provincia);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca obras por localidad.
     *
     * @param localidad
     * @return
     * @throws Exception
     */
    @GetMapping("/search/localidad")
    public List<ObrasActivasDTO> searchForLocalidad(@RequestParam String localidad) throws Exception {
        try {
            return obraServiceImpl.listLocalidad(localidad);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca obras por puesto de trabajo : gerente.
     *
     * @param gerente
     * @return
     * @throws Exception
     */
    @GetMapping("/search/gerente")
    public List<ObrasActivasDTO> searchForGerente(@RequestParam String gerente) throws Exception {
        try {
            return obraServiceImpl.listGerente(gerente);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca obras por puesto de trabajo : jefe
     *
     * @param jefe
     * @return
     * @throws Exception
     */
    @GetMapping("/search/jefe")
    public List<ObrasActivasDTO> searchForJefe(@RequestParam String jefe) throws Exception {
        try {
            return obraServiceImpl.listJefe(jefe);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca obras por puesto de trabajo : administrativo.
     *
     * @param administrativo
     * @return
     * @throws Exception
     */
    @GetMapping("/search/administrativo")
    public List<ObrasActivasDTO> searchForAdministrativo(@RequestParam String administrativo) throws Exception {
        try {
            return obraServiceImpl.listAdministrativo(administrativo);
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
    @DeleteMapping("/inactive/{id}")
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
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateObra(@PathVariable int id, @RequestBody Obra entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(obraServiceImpl.update(id, completeCamposObras.obraCamposMod(entity)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }
}
