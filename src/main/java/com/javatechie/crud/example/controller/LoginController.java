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

    private UsuarioServiceImpl userServiceImpl;

    private MapperUsuariosDTO mapperUsuariosDTO;

    public LoginController(UsuarioServiceImpl userServiceImpl, MapperUsuariosDTO mapperUsuariosDTO) {
        this.userServiceImpl = userServiceImpl;
        this.mapperUsuariosDTO = mapperUsuariosDTO;
    }

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @PostMapping()
    public ResponseEntity<?> generate(@RequestHeader String user, @RequestHeader String password) throws Exception {
        String userName = user.toUpperCase();
        String pass = password.toUpperCase();
        Usuario usuario = userServiceImpl.getPorLogin(userName);

        if (usuario == null || !usuario.getLogin().equals(userName)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\" usuario incorrecto.\"}");        }
        if (!BCrypt.checkpw(pass, usuario.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\" password incorrecto.\"}");
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
                .setExpiration(new Date(System.currentTimeMillis() + 60000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}
