package com.javatechie.crud.example.utils.mapperDto;

import com.javatechie.crud.example.dto.MaquinistaDTO;
import com.javatechie.crud.example.entity.Maquinista;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperMaquinistasDTO {

    ModelMapper modelMapper = new ModelMapper();

    public MaquinistaDTO mapper(Maquinista maquinista){
        return modelMapper.map(maquinista, MaquinistaDTO.class);
    }

    public List<MaquinistaDTO> mapperDtoConsultaMaquinistas(Page<Maquinista> entities) throws Exception {
        try {
            List<MaquinistaDTO> entitiesDto = new ArrayList<>();
            entities.stream().forEach(entity -> {
                try {
                    entitiesDto.add(mapperActivoinactivo(entity));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            return entitiesDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<MaquinistaDTO> mapperDtoMaquinistaActivo(Page<Maquinista> entities) throws Exception {
        try {
            List<MaquinistaDTO> entitiesDto = new ArrayList<>();
            entities.stream().forEach(entity -> entitiesDto.add(modelMapper.map(entity, MaquinistaDTO.class)));
            return entitiesDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private MaquinistaDTO mapperActivoinactivo(Maquinista entity) throws Exception {
        try {
            MaquinistaDTO dto = MaquinistaDTO.builder()
                    .id(entity.getMaquinistaId())
                    .nombre(entity.getNombre())
                    .apellido(entity.getApellido())
                    .legajo(entity.getLegajo())
                    .build();
            if (entity.getFechaBaja() == null && entity.getHoraBaja() == null) {
                dto.setActivo("SI");
            } else {
                dto.setActivo("NO");
            }
            return dto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
