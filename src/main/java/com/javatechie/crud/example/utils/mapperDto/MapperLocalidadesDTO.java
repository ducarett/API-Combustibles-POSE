package com.javatechie.crud.example.utils.mapperDto;

import com.javatechie.crud.example.dto.LocalidadConsultaDTO;
import com.javatechie.crud.example.dto.LocalidadDTO;
import com.javatechie.crud.example.entity.Localidad;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MapperLocalidadesDTO {

    public static List<LocalidadDTO> mapperDtoLocalidades(Page<Localidad> entities) throws Exception {
        try {
            List<LocalidadDTO> entitiesDto = new ArrayList<>();
            entities.forEach(entity -> {
                try {
                    entitiesDto.add(mapper(entity));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            return entitiesDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static LocalidadDTO mapper(Localidad entity) throws Exception {
        try {
            LocalidadDTO dto = LocalidadDTO.builder()
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
