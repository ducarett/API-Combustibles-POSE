package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.dto.UserLoginDto;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.service.Impl.UsuarioServiceImpl;
import com.javatechie.crud.example.utils.constantes.Constant;
import com.javatechie.crud.example.utils.mapperDto.MapperUsuariosDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private UsuarioServiceImpl userServiceImpl;

    @Autowired
    private MapperUsuariosDTO mapperUsuariosDTO;


    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PostMapping()
    public ResponseEntity<UserLoginDto> generate(@RequestHeader String user, @RequestHeader String password) throws Exception {

        Usuario usuario = userServiceImpl.getPorLogin(user);

        if (usuario == null || !usuario.getLogin().equals(user)) {
            throw new Exception("{\"error\":\" usuario incorrecto.\"}");
            //return new ResponseEntity<String>("{\"error\":\" usuario incorrecto.\"}", HttpStatus.BAD_REQUEST);
        }
        if (!BCrypt.checkpw(password, usuario.getPassword())) {
            throw new Exception("{\"error\":\" password incorrecto.\"}");
            //return new ResponseEntity<String>("{\"error\":\" password incorrecto.\"}", HttpStatus.BAD_REQUEST);
        }

        UserLoginDto userLoginDto = mapperUsuariosDTO.mapperUserToUserDto(usuario);
        userLoginDto.setJwt(getJWTToken(user, usuario.getTipoUsuario().getDescripcion()));
        return ResponseEntity.status(HttpStatus.OK).body(userLoginDto);
    }

    @GetMapping()
    @RolesAllowed(Constant.ROL_ADMINISTRADOR)
    public String test() throws Exception {
        return "test";
    }

    private String getJWTToken(String username, String rol) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_" + rol);

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
