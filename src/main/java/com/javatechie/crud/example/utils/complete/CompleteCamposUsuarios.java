package com.javatechie.crud.example.utils.complete;

import com.javatechie.crud.example.entity.*;
import com.javatechie.crud.example.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

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
    public Usuario usuarioCamposAlta(Usuario entity, Integer adminID) throws Exception {
        try {
            entity.setFechaAlta(LocalDate.now());
            entity.setHoraAlta(LocalDateTime.now());
            entity.setNombre(entity.getNombre().toUpperCase());
            entity.setApellido(entity.getApellido().toUpperCase());
            entity.setUsuarioAlta(adminID);
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

    public Usuario setDatosModificados(Usuario usuarioAct, Usuario usuarioMod,Integer adminId) {
        usuarioAct.setNombre(usuarioMod.getNombre());
        usuarioAct.setApellido(usuarioMod.getApellido());
        usuarioAct.setMail(usuarioMod.getMail());
        usuarioAct.setLegajo(usuarioMod.getLegajo());
        usuarioAct.setCelular(usuarioMod.getCelular());
        usuarioAct.setCargo(usuarioMod.getCargo());
        usuarioAct.setLogin(usuarioMod.getLogin());
        usuarioAct.setFechaMod(LocalDate.now());
        usuarioAct.setHoraMod(LocalDateTime.now());
        usuarioAct.setUsuarioMod(adminId);
        return usuarioMod;
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

    public boolean verificarDatosEnBase(Usuario usuario) throws Exception {
        try {
            if (comprobarLegajo(usuario.getLegajo())) {
                throw new Exception("Este legajo ya existe!");
            }
            if (comprobarCelular(usuario.getCelular())) {
                throw new Exception("Este celular ya existe!");
            }
            if (comprobarMail(usuario.getMail())) {
                throw new Exception("Este mail ya existe!");
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean verificarIgualdadEnBase(Usuario usuario, Integer id) throws Exception {
        try {
            Optional<Usuario> optional = usuarioRepository.findById(id);
            Usuario verificador = optional.get();

            if (verificador.getLegajo() != usuario.getLegajo()) {
                if (comprobarLegajo(usuario.getLegajo())) {
                    throw new Exception("Este legajo ya existe!");
                }
            } else if (!verificador.getCelular().equals(usuario.getCelular())) {
                if (comprobarCelular(usuario.getCelular())) {
                    throw new Exception("Este celular ya existe!");
                }
            } else if (!verificador.getMail().equals(usuario.getMail())) {
                if (comprobarMail(usuario.getMail())) {
                    throw new Exception("Este mail ya existe!");
                }
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean verificarUsername(Usuario usuario,Integer id) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        Usuario verificador = optional.get();
        return !verificador.getNombre().equals(usuario.getNombre()) || !verificador.getApellido().equals(usuario.getApellido()) ? true : false;
    }
}


