package com.javatechie.crud.example.utils.complete;

import com.javatechie.crud.example.entity.*;
import com.javatechie.crud.example.service.Impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Service
public class CompleteCamposUsuarios {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;



    public Usuario usuarioCamposMod(Usuario entity) throws Exception {
        try {
            entity.setFechaMod(LocalDate.now());
            entity.setHoraMod(LocalDateTime.now());
            Usuario usuarioMod = Usuario.builder()
                    .usuarioId(1).build();
            entity.setUsuarioMod(usuarioMod.getUsuarioId());
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * setea los campos del alta de localidad con datos del sistema.
     *
     * @param entity
     * @throws Exception
     */

    public Usuario usuarioCamposAlta(Usuario entity) throws Exception {
        try {
            entity.setFechaAlta(LocalDate.now());
            entity.setHoraAlta(LocalDateTime.now());
            entity.setNombre(entity.getNombre().toUpperCase());
            entity.setApellido(entity.getApellido().toUpperCase());
            entity.setUsuarioAlta(usuarioServiceImpl.findById(1).getUsuarioId());//esta hardcodeado
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public Usuario usuarioCamposBaja(Usuario entity) throws Exception {
        try {
            entity.setFechaBaja(LocalDate.now());
            entity.setHoraBaja(LocalDateTime.now());
            Usuario usuarioBaja = Usuario.builder()
                    .usuarioId(1)
                    .apellido("Schwarz".toUpperCase())
                    .nombre("sDavid".toUpperCase()).build();
            entity.setUsuarioBaja(usuarioBaja.getUsuarioId());
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }






}
