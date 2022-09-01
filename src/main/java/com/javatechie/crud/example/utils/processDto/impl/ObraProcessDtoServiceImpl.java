package com.javatechie.crud.example.utils.processDto.impl;

import com.javatechie.crud.example.dto.ObraDTO;
import com.javatechie.crud.example.service.interfaz.ObraService;
import com.javatechie.crud.example.utils.mapperDto.MapperObrasDTO;
import com.javatechie.crud.example.utils.processDto.interfaze.ProcessDtoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObraProcessDtoServiceImpl implements ProcessDtoService<ObraDTO, Integer> {

    private final static String NO_EXIST = "No se encontro ningun maquinista";
    private ObraService obraService;
    private MapperObrasDTO mapperObrasDTO;


    public ObraProcessDtoServiceImpl(ObraService obraService, MapperObrasDTO mapperObrasDTO) {
        this.obraService = obraService;
        this.mapperObrasDTO = mapperObrasDTO;
    }

    @Override
    public List<ObraDTO> listarActivos(Pageable pageable) throws Exception {
        try {
            return listar(obraService.listActivos(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ObraDTO> listarInactivos(Pageable pageable) throws Exception {
        try {
            return listar(obraService.listInactivos(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ObraDTO> listarTodos(Pageable pageable) throws Exception {
        try {
            return listar(obraService.listAllObras(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ObraDTO> listar(Page page) throws Exception {
        try {
            if (page.isEmpty()) {
                throw new Exception(NO_EXIST);
            }
            return mapperObrasDTO.mapperDtoObras(page);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

 /*   @Override
    public ObraDTO buscarPorNombre(String nombre) throws Exception {
        try {
            Obra obra = obraService.
            return ;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ObraDTO buscarPorLegajo(Integer legajo) throws Exception {
        try {
            return mapperObrasDTO.mapper(obraService.getByLegajo(legajo));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ObraDTO buscarPorApellido(String apellido) throws Exception {
        try {
            return mapperObrasDTO.mapper(obraService.getByApellido(apellido));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

  */
}
