package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.security.model.JwtUser;
import com.javatechie.crud.example.security.seguridad.JwtGenerator;
import com.javatechie.crud.example.service.Impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private UsuarioServiceImpl userServiceImpl;

    private JwtGenerator jwtGenerator;

    public LoginController(JwtGenerator jwtGenerator) throws Exception {
        this.jwtGenerator = jwtGenerator;
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PostMapping()
    public ResponseEntity<String> generate(@RequestHeader String user, @RequestHeader String password) throws Exception {

        JwtUser jwtUser;
        Usuario usuario = userServiceImpl.getPorLogin(user);

        if (usuario == null || !usuario.getLogin().equals(user)) {
            return new ResponseEntity<String>("{\"error\":\" usuario incorrecto.\"}", HttpStatus.BAD_REQUEST);
        }
        if (!BCrypt.checkpw(password, usuario.getPassword())) {
            return new ResponseEntity<String>("{\"error\":\" password incorrecto.\"}", HttpStatus.BAD_REQUEST);
        }

        jwtUser = existUser(usuario);

        if (jwtUser != null) {
            return new ResponseEntity<String>(jwtGenerator.generate(jwtUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }
    }

    private JwtUser existUser(Usuario usuario) throws Exception {
        try {
            JwtUser jwtUser = new JwtUser();
            jwtUser.setUserName(usuario.getLogin());
            jwtUser.setId(usuario.getUsuarioId());
            jwtUser.setRole(usuario.getTipoUsuario().getDescripcion());
            return jwtUser;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
