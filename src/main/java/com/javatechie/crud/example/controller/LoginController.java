package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.service.Impl.UsuarioServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private UsuarioServiceImpl userServiceImpl;


    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PostMapping()
    public ResponseEntity<String> generate(@RequestHeader String user, @RequestHeader String password) throws Exception {

        Usuario usuario = userServiceImpl.getPorLogin(user);

        if (usuario == null || !usuario.getLogin().equals(user)) {
            return new ResponseEntity<String>("{\"error\":\" usuario incorrecto.\"}", HttpStatus.BAD_REQUEST);
        }
        if (!BCrypt.checkpw(password, usuario.getPassword())) {
            return new ResponseEntity<String>("{\"error\":\" password incorrecto.\"}", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>(getJWTToken(user), HttpStatus.OK);
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}
