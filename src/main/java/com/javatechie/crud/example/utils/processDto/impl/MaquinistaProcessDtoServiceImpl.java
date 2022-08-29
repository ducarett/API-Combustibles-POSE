package com.javatechie.crud.example.utils.processDto.impl;

import com.javatechie.crud.example.dto.MaquinistaDTO;
import com.javatechie.crud.example.entity.Maquinista;
import com.javatechie.crud.example.service.interfaz.MaquinistaService;
import com.javatechie.crud.example.utils.processDto.interfaze.ProcessDtoService;
import com.javatechie.crud.example.utils.mapperDto.MapperMaquinistasDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MaquinistaProcessDtoServiceImpl implements ProcessDtoService<MaquinistaDTO, Integer> {
    private MaquinistaService maquinistaService;
    private MapperMaquinistasDTO mapperMaquinistasDTO;

    public MaquinistaProcessDtoServiceImpl(MaquinistaService maquinistaService, MapperMaquinistasDTO mapperMaquinistasDTO) {
        this.maquinistaService = maquinistaService;
        this.mapperMaquinistasDTO = mapperMaquinistasDTO;
    }

    @Override
    public List<MaquinistaDTO> listarActivos(Pageable pageable) throws Exception {
        try {
            Page<Maquinista> maquinistas = maquinistaService.listActivos(pageable);
            if (maquinistas.isEmpty()) {
                throw new Exception("No se encontro ningun maquinista activo");
            }
            return mapperMaquinistasDTO.mapperDtoMaquinistaActivo(maquinistas);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<MaquinistaDTO> listarTodos(Pageable pageable) throws Exception {
        try {
            Page<Maquinista> maquinistas =  maquinistaService.findAll(pageable);
            if (maquinistas.isEmpty()) {
                throw new Exception("No se encontro ningun maquinista");
            }
            return mapperMaquinistasDTO.mapperDtoConsultaMaquinistas(maquinistas);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public MaquinistaDTO buscarPorNombre(String nombre) throws Exception {
        try {
            return mapperMaquinistasDTO.mapper(maquinistaService.getByNombre(nombre));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public MaquinistaDTO buscarPorLegajo(Integer legajo) throws Exception {
        try {
            return mapperMaquinistasDTO.mapper(maquinistaService.getByLegajo(legajo));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public MaquinistaDTO buscarPorApellido(String apellido) throws Exception {
        try {
            return mapperMaquinistasDTO.mapper(maquinistaService.getByApellido(apellido));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
