package com.javatechie.crud.example.security.seguridad;

import com.javatechie.crud.example.security.constants.Constants;
import com.javatechie.crud.example.security.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {
    public String generate(JwtUser jwtUser) {
        Claims claims = Jwts.claims().setSubject((jwtUser.getUserName()));
        claims.put(Constants.USER_ID, String.valueOf((jwtUser.getId())));
        claims.put(Constants.ROLE, jwtUser.getRole());
        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, Constants.YOUR_SECRET)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 43500000))
                .compact();

    }
}
