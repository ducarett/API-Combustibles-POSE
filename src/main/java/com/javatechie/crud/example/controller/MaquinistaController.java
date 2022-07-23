package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.utils.complete.CompleteCamposMaquinistas;
import com.javatechie.crud.example.dto.MaquinistaConsultaDTO;
import com.javatechie.crud.example.entity.Maquinista;
import com.javatechie.crud.example.service.Impl.MaquinistaServiceImpl;
import com.javatechie.crud.example.utils.mapperDto.MapperMaquinistasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/maquinista")
@RestController
public class MaquinistaController {
    @Autowired
    private MaquinistaServiceImpl maquinistaServiceImpl;
    @Autowired
    private CompleteCamposMaquinistas completeCampos;
    @Autowired
    private MapperMaquinistasDTO mapperMaquinistasDTO;

    @PostMapping("/create")
    public ResponseEntity<?> crearMaquinista(@RequestBody Maquinista entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaServiceImpl.save(completeCampos.maquinistaCamposAlta(entity)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
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
    // @Secured(("ADMINISTRADOR"))
    @PutMapping("/update/{id}")
    public ResponseEntity<?> modificarMaquinista(@PathVariable int id, @RequestBody Maquinista entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaServiceImpl.update(id, completeCampos.maquinistaCamposMod(entity)));
        } catch (
                Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }

    }

    /**
     * Desactiva el User.
     *
     * @param id
     * @return
     */
    //@Secured(("ADMINISTRADOR"))
    @DeleteMapping("/inactive/{id}")
    public ResponseEntity<?> inactivarMaquinista(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(maquinistaServiceImpl.bajaMaquinista(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    /**
     * Retorna un maquinista segun el ID.
     *
     * @param id
     * @return
     */
    // @Secured("ADMINISTRATIVO")
    @GetMapping("/")
    public ResponseEntity<?> getMaquinista(@RequestParam Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaServiceImpl.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    @GetMapping("/active")
    public ResponseEntity<?> getUsuariosActivos() {
        try {
            List<Maquinista> maquinistas = maquinistaServiceImpl.listActivos();
            return ResponseEntity.status(HttpStatus.OK).body(mapperMaquinistasDTO.mapperDtoMaquinistaActivo(maquinistas));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    @GetMapping("/nombre")
    public ResponseEntity<?> getMaquinistaNombre(@RequestParam String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaServiceImpl.buscarPorNombre(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    @GetMapping("/apellido")
    public ResponseEntity<?> getMaquinistaApellido(@RequestParam String apellido) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaServiceImpl.buscarPorApellido(apellido));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    @GetMapping("/legajo")
    public ResponseEntity<?> getMaquinistaLegajo(@RequestParam Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaServiceImpl.buscarPorLegajo(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    @GetMapping("/getAll")
    public List<MaquinistaConsultaDTO> getAllMaquinistas() throws Exception {
        try {
            return mapperMaquinistasDTO.mapperDtoConsultaMaquinistas(maquinistaServiceImpl.findAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
