package com.javatechie.crud.example.utils.complete;

import com.javatechie.crud.example.entity.Obra;
import com.javatechie.crud.example.entity.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class CompleteCamposObras {

    public void obraCamposBaja(Obra entity) throws Exception {
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


    /**
     * setea los campos del alta de localidad con datos del sistema.
     *
     * @param entity
     * @throws Exception
     */

    public Obra obraCamposAlta(Obra entity, Integer adminId) throws Exception {
        try {
            entity.setFechaAlta(LocalDate.now());
            entity.setHoraAlta(LocalDateTime.now());
            entity.setUsuarioAlta(Usuario.builder().usuarioId(adminId).build());
            entity.setDescripcion(entity.getDescripcion().toUpperCase());
            entity.setNombreObra(entity.getNombreObra().toUpperCase());
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Obra obraCamposMod(Obra entity) throws Exception {
        try {
            entity.setFechaMod(LocalDate.now());
            entity.setHoraMod(LocalDateTime.now());
            Usuario usuarioMod = Usuario.builder()
                    .usuarioId(1).build();
            entity.setUsuarioMod(usuarioMod);
            entity.setDescripcion(entity.getDescripcion().toUpperCase());
            entity.setNombreObra(entity.getNombreObra().toUpperCase());
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
