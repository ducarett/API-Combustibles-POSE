package com.javatechie.crud.example.utils.mapperDto;

import com.javatechie.crud.example.dto.LocalidadConsultaDTO;
import com.javatechie.crud.example.dto.LocalidadDTO;
import com.javatechie.crud.example.entity.Localidad;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MapperLocalidadesDTO {

    ModelMapper modelMapper = new ModelMapper();

    public List<LocalidadDTO> mapperDtoLocalidadActivo(List<Localidad> entities) throws Exception {
        try {
            List<LocalidadDTO> entitiesDto = new ArrayList<>();
            entities.forEach(entity -> entitiesDto.add(modelMapper.map(entity, LocalidadDTO.class)));
            return entitiesDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public LocalidadConsultaDTO mapperActivoinactivo(Localidad entity) throws Exception {
        try {
            LocalidadConsultaDTO dto = LocalidadConsultaDTO.builder()
                    .provincia(entity.getProvincia().getDescriptionProvincia().toUpperCase())
                    .localidad(entity.getDescripcionLocalidad().toUpperCase())
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
