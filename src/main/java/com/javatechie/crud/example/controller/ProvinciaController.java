package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.Provincia;
import com.javatechie.crud.example.service.Impl.ProvinciaServiceImpl;
import com.javatechie.crud.example.utils.constantes.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/provincia")
@CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*")
public class ProvinciaController {

    @Autowired
    private ProvinciaServiceImpl provinciaService;

    @GetMapping("")
    @RolesAllowed({Constant.ROL_ADMINISTRADOR})
    public List<Provincia> getAllProvincias() throws Exception {
        try {
            return provinciaService.listaProvincias();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
