package com.javatechie.crud.example.utils.mapperDto;

import com.javatechie.crud.example.dto.MaquinistaActivoDTO;
import com.javatechie.crud.example.dto.MaquinistaConsultaDTO;
import com.javatechie.crud.example.entity.Maquinista;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MapperMaquinistasDTO {

    ModelMapper modelMapper = new ModelMapper();

    public List<MaquinistaConsultaDTO> mapperDtoConsultaMaquinistas(List<Maquinista> entities) throws Exception {
        try {
            List<MaquinistaConsultaDTO> entitiesDto = new ArrayList<>();
            entities.stream().forEach(entity -> { try { entitiesDto.add(mapperActivoinactivo(entity)); } catch (Exception e) { throw new RuntimeException(e);} });
            return entitiesDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<MaquinistaActivoDTO> mapperDtoMaquinistaActivo(List<Maquinista> entities) throws Exception {
        try {
            List<MaquinistaActivoDTO> entitiesDto = new ArrayList<>();
            entities.stream().forEach(entity -> entitiesDto.add(modelMapper.map(entity, MaquinistaActivoDTO.class)));
            return entitiesDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private MaquinistaConsultaDTO mapperActivoinactivo(Maquinista entity) throws Exception {
        try {
            MaquinistaConsultaDTO dto = MaquinistaConsultaDTO.builder()
                    .nombre(entity.getNombre())
                    .apellido(entity.getApellido())
                    .legajo(entity.getLegajo())
                    .build();
            if (entity.getFechaBaja() == null && entity.getHoraBaja() == null) {
                dto.setActivo("si");
            } else {
                dto.setActivo("no");
            }
            return dto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
