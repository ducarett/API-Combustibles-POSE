package com.javatechie.crud.example.utils.processDto.impl;

import com.javatechie.crud.example.dto.MaquinistaDTO;
import com.javatechie.crud.example.service.interfaz.MaquinistaService;
import com.javatechie.crud.example.utils.processDto.interfaze.ProcessDtoService;
import com.javatechie.crud.example.utils.mapperDto.MapperMaquinistasDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaquinistaProcessListServiceImpl implements ProcessDtoService<MaquinistaDTO, Integer> {

    private final static String NO_EXIST = "No se encontro ningun maquinista";
    private MaquinistaService maquinistaService;
    private MapperMaquinistasDTO mapperMaquinistasDTO;

    public MaquinistaProcessListServiceImpl(MaquinistaService maquinistaService,
                                            MapperMaquinistasDTO mapperMaquinistasDTO) {
        this.maquinistaService = maquinistaService;
        this.mapperMaquinistasDTO = mapperMaquinistasDTO;
    }

    @Override
    public List<MaquinistaDTO> listarActivos(Pageable pageable) throws Exception {
        try {
            return mapperMaquinistasDTO.mapperDtoMaquinistaActivo(maquinistaService.listActivos(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<MaquinistaDTO> listarInactivos(Pageable pageable) throws Exception {
        try {
            return mapperMaquinistasDTO.mapperDtoMaquinistaActivo(maquinistaService.listInactivos(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<MaquinistaDTO> listarTodos(Pageable pageable) throws Exception {
        try {
            return mapperMaquinistasDTO.mapperDtoConsultaMaquinistas(maquinistaService.listAllMaquinistas(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<MaquinistaDTO> listar(Page page) throws Exception {
        try {
            if (page.isEmpty()) throw new Exception(NO_EXIST);
            return mapperMaquinistasDTO.mapperDtoConsultaMaquinistas(page);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
