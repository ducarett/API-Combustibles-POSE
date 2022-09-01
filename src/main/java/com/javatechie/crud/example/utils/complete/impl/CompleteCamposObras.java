package com.javatechie.crud.example.utils.complete.impl;

import com.javatechie.crud.example.entity.Maquinista;
import com.javatechie.crud.example.entity.Obra;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.utils.complete.interfaz.CompletarCampos;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class CompleteCamposObras implements CompletarCampos<Obra, Integer> {

    @Override
    public Obra alta(Obra entity, Integer altaId) throws Exception {
        try {
            entity.setFechaAlta(LocalDate.now());
            entity.setHoraAlta(LocalDateTime.now());
            entity.setUsuarioAlta(Usuario.builder().usuarioId(altaId).build());
            entity.setDescripcion(entity.getDescripcion().toUpperCase());
            entity.setNombreObra(entity.getNombreObra().toUpperCase());
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void baja(Obra entity, Integer bajaId) throws Exception {
        try {
            entity.setFechaBaja(LocalDate.now());
            entity.setHoraBaja(LocalDateTime.now());
            entity.setUsuarioBaja(Usuario.builder().usuarioId(bajaId).build());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Obra modificar(Obra entity, Integer modId) throws Exception {
        try {
            entity.setFechaMod(LocalDate.now());
            entity.setHoraMod(LocalDateTime.now());
            entity.setUsuarioMod(Usuario.builder().usuarioId(modId).build());
            entity.setDescripcion(entity.getDescripcion().toUpperCase());
            entity.setNombreObra(entity.getNombreObra().toUpperCase());
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
