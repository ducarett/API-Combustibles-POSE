package com.javatechie.crud.example.security.seguridad;

import com.javatechie.crud.example.security.constants.Constants;
import com.javatechie.crud.example.security.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    public JwtUser validate(String token){
        JwtUser jwtUser = null;
        try{
            Claims body = Jwts.parser()
                    .setSigningKey(Constants.YOUR_SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();
            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Integer.parseInt((String) body.get(Constants.USER_ID)));
            jwtUser.setRole((String) body.get(Constants.ROLE));

        }catch (Exception e){
            System.out.println(e);
        }
        return jwtUser;
    }
}
