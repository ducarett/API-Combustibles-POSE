package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.utils.complete.CompleteCamposMaquinistas;
import com.javatechie.crud.example.dto.MaquinistaConsultaDTO;
import com.javatechie.crud.example.entity.Maquinista;
import com.javatechie.crud.example.service.Impl.MaquinistaServiceImpl;
import com.javatechie.crud.example.utils.constantes.Constant;
import com.javatechie.crud.example.utils.mapperDto.MapperMaquinistasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR})
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
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PutMapping("/update/{id}")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR})
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
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @DeleteMapping("/inactive/{id}")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR})
    public ResponseEntity<?> bajaMaquinista(@PathVariable int id) {
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
    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getMaquinista(@RequestParam Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaServiceImpl.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/active")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getUsuariosActivos(Pageable pageable) {
        try {
            Page<Maquinista> maquinistas = maquinistaServiceImpl.listActivos(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(mapperMaquinistasDTO.mapperDtoMaquinistaActivo(maquinistas));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/nombre")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getMaquinistaNombre(@RequestParam String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaServiceImpl.buscarPorNombre(nombre.toUpperCase()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/apellido")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getMaquinistaApellido(@RequestParam String apellido) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaServiceImpl.buscarPorApellido(apellido.toUpperCase()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/legajo")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public ResponseEntity<?> getMaquinistaLegajo(@RequestParam Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinistaServiceImpl.buscarPorLegajo(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("/getAll")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR, Constant.ROL_ADMINISTRATIVO})
    public List<MaquinistaConsultaDTO> getAllMaquinistas(Pageable pageable) throws Exception {
        try {
            return mapperMaquinistasDTO.mapperDtoConsultaMaquinistas(maquinistaServiceImpl.findAll(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
