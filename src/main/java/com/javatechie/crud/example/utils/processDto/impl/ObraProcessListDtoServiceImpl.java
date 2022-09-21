package com.javatechie.crud.example.utils.processDto.impl;

import com.javatechie.crud.example.dto.ObraDTO;
import com.javatechie.crud.example.service.interfaz.ObraService;
import com.javatechie.crud.example.utils.mapperDto.MapperObrasDTO;
import com.javatechie.crud.example.utils.processDto.interfaze.ProcessDtoService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObraProcessListDtoServiceImpl implements ProcessDtoService<ObraDTO, Integer> {
    private ObraService obraService;

    public ObraProcessListDtoServiceImpl(ObraService obraService) {
        this.obraService = obraService;
    }

    @Override
    public List<ObraDTO> listarActivos(Pageable pageable) throws Exception {
        try {
            return MapperObrasDTO.mapperDtoObras(obraService.listActivos(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ObraDTO> listarInactivos(Pageable pageable) throws Exception {
        try {
            return MapperObrasDTO.mapperDtoObras(obraService.listInactivos(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ObraDTO> listarTodos(Pageable pageable) throws Exception {
        try {
            return MapperObrasDTO.mapperDtoObras(obraService.listAllObras(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
