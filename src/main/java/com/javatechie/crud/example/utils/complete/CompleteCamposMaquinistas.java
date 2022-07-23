package com.javatechie.crud.example.utils.complete;

import com.javatechie.crud.example.entity.Maquinista;
import com.javatechie.crud.example.entity.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Service
public class CompleteCamposMaquinistas {

    public Maquinista maquinistaCamposMod(Maquinista entity) throws Exception {
        try {
            entity.setFechaMod(LocalDate.now());
            entity.setHoraMod(LocalDateTime.now());
            Usuario usuarioMod = Usuario.builder()
                    .usuarioId(1).build();
            entity.setUsuarioMod(usuarioMod);
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

    public Maquinista maquinistaCamposAlta(Maquinista entity) throws Exception {
        try {
            entity.setFechaAlta(LocalDate.now());
            entity.setHoraAlta(LocalDateTime.now());
            Usuario usuarioAlta = Usuario.builder()
                    .usuarioId(1).build();
            entity.setUsuarioAlta(usuarioAlta);
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

    public void maquinistaCamposBaja(Maquinista entity) throws Exception {
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

}
