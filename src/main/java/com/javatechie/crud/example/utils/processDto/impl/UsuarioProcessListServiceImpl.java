package com.javatechie.crud.example.utils.processDto.impl;

import com.javatechie.crud.example.dto.UsuarioDTO;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.service.interfaz.UsuarioService;
import com.javatechie.crud.example.utils.mapperDto.MapperUsuariosDTO;
import com.javatechie.crud.example.utils.processDto.interfaze.ProcessDtoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioProcessListServiceImpl implements ProcessDtoService<UsuarioDTO, Integer> {
    private UsuarioService usuarioService;

    public UsuarioProcessListServiceImpl(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public List<UsuarioDTO> listarActivos(Pageable pageable) throws Exception {
        try {
            return MapperUsuariosDTO.mapperDtoUsuarios(usuarioService.listarActivos(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UsuarioDTO> listarInactivos(Pageable pageable) throws Exception {
        try {
            return MapperUsuariosDTO.mapperDtoUsuarios(usuarioService.listarInactivos(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UsuarioDTO> listarTodos(Pageable pageable) throws Exception {
        try {
            return MapperUsuariosDTO.mapperDtoUsuarios(usuarioService.listAllUsuarios(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<UsuarioDTO> listaGerentes(Pageable pageable) throws Exception {
        try {
            return MapperUsuariosDTO.mapperDtoUsuarios(usuarioService.listaGerentes(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<UsuarioDTO> listaJefes(Pageable pageable) throws Exception {
        try {
            return MapperUsuariosDTO.mapperDtoUsuarios(usuarioService.listaJefes(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<UsuarioDTO> listaAdministrativos(Pageable pageable) throws Exception {
        try {
            return MapperUsuariosDTO.mapperDtoUsuarios(usuarioService.listaAdministrativos(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
