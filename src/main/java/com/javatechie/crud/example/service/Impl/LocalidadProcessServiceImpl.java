package com.javatechie.crud.example.service.Impl;

import com.javatechie.crud.example.entity.Localidad;
import com.javatechie.crud.example.entity.Obra;
import com.javatechie.crud.example.repository.ObraBaseRepository;
import com.javatechie.crud.example.service.interfaz.LocalidadService;
import com.javatechie.crud.example.service.interfaz.ObraService;
import com.javatechie.crud.example.service.interfaz.ProcessService;
import com.javatechie.crud.example.utils.verificar.impl.VerificarObraEnBaseImpl;
import org.springframework.stereotype.Service;

@Service
public class LocalidadProcessServiceImpl implements ProcessService<Localidad, Integer> {
    private LocalidadService localidadService;

    public LocalidadProcessServiceImpl(LocalidadService localidadService) {
        this.localidadService = localidadService;
    }

    @Override
    public Localidad crear(Localidad localidad, Integer adminId) throws Exception {
        try {
            return localidadService.crearLocalidad(localidad, adminId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Localidad actualizar(Integer id, Localidad localidad, Integer adminId) throws Exception {
        try {
            return localidadService.editarLocalidad(id, localidad, adminId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean baja(Integer id, Integer adminId) throws Exception {
        try {
            return localidadService.bajaLocalidad(id, adminId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Localidad buscarPorId(Integer id) throws Exception {
        try {
            return localidadService.getPorId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
