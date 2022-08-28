package com.javatechie.crud.example.utils.complete.impl;

import com.javatechie.crud.example.entity.Maquinista;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.utils.complete.interfaz.CompletarCampos;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CompletarCamposMaquinista implements CompletarCampos<Maquinista, Integer> {

    public CompletarCamposMaquinista() {
    }

    @Override
    public Maquinista alta(Maquinista entity, Integer altaId) throws Exception {
        try {
            entity.setFechaAlta(LocalDate.now());
            entity.setHoraAlta(LocalDateTime.now());
            entity.setUsuarioAlta(Usuario.builder().usuarioId(altaId).build());
            entity.setApellido(entity.getApellido().toUpperCase());
            entity.setNombre(entity.getNombre().toUpperCase());
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void baja(Maquinista entity, Integer bajaId) throws Exception {
        try {
            entity.setFechaBaja(LocalDate.now());
            entity.setHoraBaja(LocalDateTime.now());
            entity.setUsuarioBaja(Usuario.builder().usuarioId(bajaId).build());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Maquinista modificar(Maquinista entity, Integer modId) throws Exception {
        try {
            entity.setFechaMod(LocalDate.now());
            entity.setHoraMod(LocalDateTime.now());
            entity.setUsuarioMod(Usuario.builder().usuarioId(modId).build());
            entity.setApellido(entity.getApellido().toUpperCase());
            entity.setNombre(entity.getNombre().toUpperCase());
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
