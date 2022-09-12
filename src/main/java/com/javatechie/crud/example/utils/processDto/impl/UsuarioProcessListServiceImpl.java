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

    private final static String NO_EXIST = "No se encontro ningun maquinista";
    private UsuarioService usuarioService;
    private MapperUsuariosDTO mapperUsuariosDTO;

    public UsuarioProcessListServiceImpl(UsuarioService usuarioService, MapperUsuariosDTO mapperUsuariosDTO) {
        this.usuarioService = usuarioService;
        this.mapperUsuariosDTO = mapperUsuariosDTO;
    }


    @Override
    public List<UsuarioDTO> listarActivos(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public List<UsuarioDTO> listarInactivos(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public List<UsuarioDTO> listarTodos(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public List<UsuarioDTO> listar(Page page) throws Exception {
        return null;
    }


    public List<UsuarioDTO> listarBusqueda(Pageable page, Usuario usuario) throws Exception {
        return usuarioService.listarBusquedaUniversal(page, usuario);
    }

}
