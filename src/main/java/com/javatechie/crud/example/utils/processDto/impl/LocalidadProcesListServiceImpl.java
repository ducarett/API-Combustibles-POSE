package com.javatechie.crud.example.utils.processDto.impl;

import com.javatechie.crud.example.dto.LocalidadDTO;
import com.javatechie.crud.example.dto.ObraDTO;
import com.javatechie.crud.example.entity.Localidad;
import com.javatechie.crud.example.entity.Obra;
import com.javatechie.crud.example.service.interfaz.LocalidadService;
import com.javatechie.crud.example.service.interfaz.ObraService;
import com.javatechie.crud.example.utils.mapperDto.MapperLocalidadesDTO;
import com.javatechie.crud.example.utils.mapperDto.MapperObrasDTO;
import com.javatechie.crud.example.utils.metodo.MetodosLocalidadUtils;
import com.javatechie.crud.example.utils.processDto.interfaze.ProcessDtoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadProcesListServiceImpl implements ProcessDtoService<LocalidadDTO, Integer> {

    private final static String NO_EXIST = "No se encontro ninguna localidad";
    private LocalidadService localidadService;
    private MapperLocalidadesDTO mapperLocalidadesDTO;
    private MetodosLocalidadUtils metodosLocalidadUtils;


    public LocalidadProcesListServiceImpl(LocalidadService localidadService, MapperLocalidadesDTO mapperLocalidadesDTO,
                                          MetodosLocalidadUtils metodosLocalidadUtils) {
        this.localidadService = localidadService;
        this.mapperLocalidadesDTO = mapperLocalidadesDTO;
        this.metodosLocalidadUtils = metodosLocalidadUtils;
    }

    @Override
    public List<LocalidadDTO> listarActivos(Pageable pageable) throws Exception {
        try {
            return listar(localidadService.listActivos(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<LocalidadDTO> listarInactivos(Pageable pageable) throws Exception {
        try {
            return listar(localidadService.listInactivos(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<LocalidadDTO> listarTodos(Pageable pageable) throws Exception {
        try {
            Page<LocalidadDTO> page = metodosLocalidadUtils.ordenarProvicnciasAlfabeticamente(localidadService.listAllObras(pageable));
            return listar(page);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<LocalidadDTO> listLocalidadesProvId(Integer id, Pageable pageable) throws Exception {
        try {
            return listar(localidadService.listarLocalidadesPorProvId(id, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public List<LocalidadDTO> listar(Page page) throws Exception {
        try {
            if (page.isEmpty()) throw new Exception(NO_EXIST);
            return mapperLocalidadesDTO.mapperDtoLocalidad(page);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
