package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.service.Impl.ProvinciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provincia")
public class ProvinciaController {

    @Autowired
    private ProvinciaServiceImpl provinciaService;

    @GetMapping("")
    public List<String> getAllProvincias() throws Exception {
        try {
            return provinciaService.listaProvincias();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
