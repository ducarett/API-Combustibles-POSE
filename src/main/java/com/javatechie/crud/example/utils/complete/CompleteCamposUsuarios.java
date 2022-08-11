package com.javatechie.crud.example.utils.complete;

import com.javatechie.crud.example.entity.*;
import com.javatechie.crud.example.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CompleteCamposUsuarios {


    private UsuarioRepository usuarioRepository;

    public CompleteCamposUsuarios(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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
            entity.setUsuarioAlta(1);//esta hardcodeado
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

    public boolean comprobarMail(String mail) {
        return !usuarioRepository.findByMail(mail).isEmpty() ? true : false;
    }

    public boolean comprobarLegajo(Integer legajo) {
        return usuarioRepository.findByLegajo(legajo) != null ? true : false;
    }

    public boolean comprobarCelular(Integer celular) {
        return !usuarioRepository.findByCelular(celular).isEmpty() ? true : false;
    }

    public Usuario setDatosModificados(Usuario usuarioAct, Usuario usuarioMod) {
        usuarioAct.setNombre(usuarioMod.getNombre());
        usuarioAct.setApellido(usuarioMod.getApellido());
        usuarioAct.setMail(usuarioMod.getMail());
        usuarioAct.setLegajo(usuarioMod.getLegajo());
        usuarioAct.setCelular(usuarioMod.getCelular());
        usuarioAct.setCargo(usuarioMod.getCargo());
        usuarioAct.setLogin(usuarioMod.getLogin());
        usuarioAct.setFechaMod(LocalDate.now());
        usuarioAct.setHoraMod(LocalDateTime.now());
        Usuario usuario = Usuario.builder()
                .usuarioId(1).build();
        usuarioAct.setUsuarioMod(usuario.getUsuarioId());
        return usuarioMod;
    }
}
