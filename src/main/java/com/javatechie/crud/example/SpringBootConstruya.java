package com.javatechie.crud.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

@SpringBootApplication
@Import(Swagger2DocumentationConfiguration.class)
public class SpringBootConstruya implements WebMvcConfigurer {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootConstruya.class, args);

    }
}
