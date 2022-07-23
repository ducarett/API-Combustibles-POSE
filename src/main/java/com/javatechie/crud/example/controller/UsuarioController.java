package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.utils.complete.CompleteCamposUsuarios;
import com.javatechie.crud.example.utils.metodo.MetodosUsuariosUtils;
import com.javatechie.crud.example.utils.mapperDto.MapperUsuariosDTO;
import com.javatechie.crud.example.dto.UserConsultaDTO;
import com.javatechie.crud.example.dto.UserDTO;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.service.Impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl userServiceImpl;

    @Autowired
    private MapperUsuariosDTO mapperUsuariosDTO;

    @Autowired
    private CompleteCamposUsuarios completeCamposUsuarios;

    @Autowired
    private MetodosUsuariosUtils metodosUsuariosUtils;

    /**
     * lista los usuarios activos.
     *
     * @return
     */
    //@Secured({"ADMINISTRADOR", "ADMINISTRATIVO"})
    @GetMapping("/active")
    public ResponseEntity<?> getUserActivos() {
        try {
            List<Usuario> usuarios = userServiceImpl.listarActivos();
            return ResponseEntity.status(HttpStatus.OK).body(mapperUsuariosDTO.mapperDtoUsuarioActivo(usuarios));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    /**
     * Lista los usuarios inactivos.
     *
     * @return
     */
    @GetMapping("/inactive")
    public ResponseEntity<?> getUerInactivos() {
        try {
            List<Usuario> usuarios = userServiceImpl.listarInactivos();
            return ResponseEntity.status(HttpStatus.OK).body(mapperUsuariosDTO.mapperDtoUsuarioInactivo(usuarios));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\" por favor intentelo mas tarde.\"}" + e.getMessage());
        }
    }

    /**
     * retorna una lista de dtos con el campo activo en si o no.
     * @return
     * @throws Exception
     */
    @GetMapping("/getAll")
    public List<UserConsultaDTO> getAllUsuarios() throws Exception {
        try {
            return mapperUsuariosDTO.mapperDtoConsultaUsuarios(userServiceImpl.findAll());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    /**
     * Retorna un User segun el ID.
     *
     * @param id
     * @return
     */
    // @Secured("ADMINISTRATIVO")
    @GetMapping("/")
    public ResponseEntity<?> getUsuario(@RequestParam Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    /**
     * Crea User nuevo, hardcodea los campos dateAdd y hourAdd con la fecha y hora actual del sistema, encripta la password.
     *
     * @param entity
     * @return
     */
    // @Secured(("ADMINISTRADOR"))
    @PostMapping("/create")
    public ResponseEntity<?> createUsuario(@RequestBody Usuario entity) { // ver de mejorar esto
        try {
            metodosUsuariosUtils.comprobarUserNameRepetido(entity.getLogin());
            entity.setPassword(BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt()));
            return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.save(completeCamposUsuarios.usuarioCamposAlta(entity)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    /**
     * Modifica la entidad User, antes de eso comprueba que los campos celular mail o legajo no esten repetidos.
     * Luego setea los campos fechaMod, horaMod con los datos actuales del sistema y el campo usuarioMod.
     * @param id
     * @param entity
     * @return
     */
    // @Secured(("ADMINISTRADOR"))
    @PutMapping("/update/{id}")
    public ResponseEntity<?> UpdateUsuario(@PathVariable Integer id, @RequestBody Usuario entity) { // ver de mejorar esto
        try {
            if (!metodosUsuariosUtils.comprobarCampos(entity,id)) {
                entity.setLogin(userServiceImpl.crearUserName(entity.getNombre(),entity.getApellido()));
                return ResponseEntity.status(HttpStatus.OK).body(userServiceImpl.update(id, completeCamposUsuarios.usuarioCamposMod(entity)));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"uno de los campos ya existe\"}");
        } catch (Exception e) {
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
    public ResponseEntity<?> bajaUsuario(@PathVariable int id) { // ver de mejorar esto
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userServiceImpl.bajaUsuario(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error: por favor intentelo mas tarde.\"}");
        }
    }

    /**
     * muestra el nombre de usuario del usuario logueado.
     *
     * @param auth
     * @return
     * @throws Exception
     */
    @GetMapping("/ver")
    public String getUserLogueado(Authentication auth) throws Exception {
        try {
            String user = auth.getName();
            return user;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca usuarios por nombre.
     * @param name
     * @return
     * @throws Exception
     */
    @GetMapping("/search/nombre")
    public List<UserDTO> searchForNombre(@RequestParam String name) throws Exception {
        try {
            return userServiceImpl.listarPorNombre(name);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca usuarios por apellido.
     * @param lastName
     * @return
     * @throws Exception
     */
    @GetMapping("/search/apellido")
    public List<UserDTO> searchForApellido(@RequestParam String lastName) throws Exception {
        try {
            return userServiceImpl.listarPorApellido(lastName);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca usuarios por cargo.
     * @param position
     * @return
     * @throws Exception
     */
    @GetMapping("/search/cargo")
    public List<UserDTO> searchForCargo(@RequestParam String position) throws Exception {
        try {
            return userServiceImpl.listarPorCargo(position.toUpperCase());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca usuarios por username(login).
     * @param userName
     * @return
     * @throws Exception
     */
    @GetMapping("/search/login")
    public UserDTO searchForLogin(@RequestParam String userName) throws Exception {
        try {
            return userServiceImpl.buscarPorLogin(userName);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busca usuarios por legajo.
     * @param file
     * @return
     * @throws Exception
     */
    @GetMapping("/search/legajo")
    public List<UserDTO> searchForlegajo(@RequestParam Integer file) throws Exception {
        try {
            return userServiceImpl.listarPorLegajo(file);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * cambia la clave de usuario y lo persiste y lo retorna.
     * @param pass
     * @param passConfirmacion
     * @param id
     * @return
     * @throws Exception
     */
    @PutMapping("/updatePass")
    public Usuario updatePassword(@RequestParam String pass,@RequestParam String passConfirmacion,@RequestParam Integer id) throws Exception{
        try {
            if (pass.equals(passConfirmacion)) {
                return metodosUsuariosUtils.cambiarClave(pass, id);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * retorna una lista de gerentes con nombre y apellido ordenados alfabeticamente.
     * @return
     * @throws Exception
     */
    @GetMapping("/gerentes")
    public List<String> listGerentes() throws Exception{
        try {
            return userServiceImpl.listaGerentes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * retorna una lista de jefes con nombre y apellido ordenados alfabeticamente.
     * @return
     * @throws Exception
     */
    @GetMapping("/jefes")
    public List<String> listJefes() throws Exception{
        try {
            return userServiceImpl.listaJefes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * retorna una lista de administrativos rodenados alfabeticamente.
     * @return
     * @throws Exception
     */
    @GetMapping("/administrativos")
    public List<String> listAdministrativos() throws Exception{
        try {
            return userServiceImpl.listaAdministrativos();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
