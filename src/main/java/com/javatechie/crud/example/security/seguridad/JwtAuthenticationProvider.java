package com.javatechie.crud.example.security.seguridad;

import com.javatechie.crud.example.security.model.JwtAuthenticationToken;
import com.javatechie.crud.example.security.model.JwtUser;
import com.javatechie.crud.example.security.model.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtValidator jwtValidator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();
        JwtUser jwtUser = jwtValidator.validate(token);

        if (jwtUser == null)
            throw new RuntimeException("Jwt es incorrecto");

        List<GrantedAuthority> grantedAuthority = AuthorityUtils.commaSeparatedStringToAuthorityList(jwtUser.getRole());
        return  new JwtUserDetails(jwtUser.getUserName(),token,jwtUser.getId(),grantedAuthority);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
