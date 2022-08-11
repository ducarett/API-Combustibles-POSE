package com.javatechie.crud.example;


import com.javatechie.crud.example.securityJwt.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

@SpringBootApplication
@Import(Swagger2DocumentationConfiguration.class)
public class SpringBootConstruya implements WebMvcConfigurer {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootConstruya.class, args);

    }

    @EnableWebSecurity
    @Configuration
    @EnableGlobalMethodSecurity(jsr250Enabled=true)
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().configurationSource(corsConfigurationSource());
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/login").permitAll()
                    .anyRequest().authenticated();
        }
        //This can be customized as required
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            List<String> allowOrigins = Arrays.asList("*");
            configuration.setAllowedOrigins(allowOrigins);
            configuration.setAllowedMethods(singletonList("*"));
            configuration.setAllowedHeaders(singletonList("*"));
            //in case authentication is enabled this flag MUST be set, otherwise CORS requests will fail
            configuration.setAllowCredentials(true);
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }
    }
}
