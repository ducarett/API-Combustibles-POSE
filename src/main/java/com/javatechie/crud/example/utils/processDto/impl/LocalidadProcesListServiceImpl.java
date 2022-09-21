package com.javatechie.crud.example.utils.processDto.impl;

import com.javatechie.crud.example.dto.LocalidadDTO;
import com.javatechie.crud.example.service.interfaz.LocalidadService;
import com.javatechie.crud.example.utils.mapperDto.MapperLocalidadesDTO;
import com.javatechie.crud.example.utils.metodo.MetodosLocalidadUtils;
import com.javatechie.crud.example.utils.processDto.interfaze.ProcessDtoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadProcesListServiceImpl implements ProcessDtoService<LocalidadDTO, Integer> {
    private LocalidadService localidadService;
    private MetodosLocalidadUtils metodosLocalidadUtils;


    public LocalidadProcesListServiceImpl(LocalidadService localidadService, MetodosLocalidadUtils metodosLocalidadUtils) {
        this.localidadService = localidadService;
        this.metodosLocalidadUtils = metodosLocalidadUtils;
    }

    @Override
    public List<LocalidadDTO> listarActivos(Pageable pageable) throws Exception {
        try {
            return MapperLocalidadesDTO.mapperDtoLocalidades(localidadService.listActivos(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<LocalidadDTO> listarInactivos(Pageable pageable) throws Exception {
        try {
            return MapperLocalidadesDTO.mapperDtoLocalidades(localidadService.listInactivos(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<LocalidadDTO> listarTodos(Pageable pageable) throws Exception {
        try {
            return metodosLocalidadUtils.ordenarProvicnciasAlfabeticamente(localidadService.listAllObras(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<LocalidadDTO> listLocalidadesProvId(Integer id, Pageable pageable) throws Exception {
        try {
            return MapperLocalidadesDTO.mapperDtoLocalidades(localidadService.listarLocalidadesPorProvId(id, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
