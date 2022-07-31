package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.service.Impl.CargoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/cargos")
@RestController
public class CargoController {
    @Autowired
    private CargoServiceImpl cargoServiceImpl;

    @CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
    @GetMapping("")
    public ResponseEntity<?> getAllCargos() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cargoServiceImpl.listCargos());

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
