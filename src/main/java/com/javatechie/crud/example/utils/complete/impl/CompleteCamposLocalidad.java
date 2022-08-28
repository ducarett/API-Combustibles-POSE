package com.javatechie.crud.example.utils.complete.impl;

import com.javatechie.crud.example.entity.Localidad;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.utils.complete.interfaz.CompletarCampos;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Service
public class CompleteCamposLocalidad implements CompletarCampos<Localidad,Integer> {

    public CompleteCamposLocalidad() {
    }

    @Override
    public Localidad alta(Localidad entity, Integer altaId) throws Exception {
        try {
            entity.setFechaAlta(LocalDate.now());
            entity.setHoraAlta(LocalDateTime.now());
            entity.setDescripcionLocalidad(entity.getDescripcionLocalidad().toUpperCase());
            entity.setUsuarioAlta(Usuario.builder().usuarioId(altaId).build());
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void baja(Localidad entity, Integer bajaId) throws Exception {
        try {
            entity.setFechaBaja(LocalDate.now());
            entity.setHoraBaja(LocalDateTime.now());
            Usuario usuarioBaja = Usuario.builder()
                    .usuarioId(1)
                    .apellido("Schwarz")
                    .nombre("sDavid").build();
            entity.setUsuarioBaja(usuarioBaja);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Localidad modificar(Localidad entity, Integer modId) throws Exception {
        try {
            entity.setFechaMod(LocalDate.now());
            entity.setHoraMod(LocalDateTime.now());
            Usuario usuarioMod = Usuario.builder()
                    .usuarioId(1).build();
            entity.setUsuarioMod(usuarioMod);
            entity.setDescripcionLocalidad(entity.getDescripcionLocalidad().toUpperCase());
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
