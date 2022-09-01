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

    ModelMapper modelMapper = new ModelMapper();

    public List<LocalidadDTO> mapperDtoLocalidad(Page<Localidad> entities) throws Exception {
        try {
            List<LocalidadDTO> entitiesDto = new ArrayList<>();
            entities.forEach(entity -> entitiesDto.add(modelMapper.map(entity, LocalidadDTO.class)));
            return entitiesDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public LocalidadConsultaDTO mapperDTO(Localidad entity) throws Exception {
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

   /* ModelMapper model = new ModelMapper();
        return Optional
                .ofNullable(localidadRepository.findAllForProvincia(provinciaId, pageable).stream()
                        .filter(localidad -> localidad.getFechaBaja() == null)
            .sorted(Comparator.comparing(localidad -> localidad.getDescripcionLocalidad()))
            .map(localidad -> model.map(localidad, LocalidadDTO.class))
            .collect(Collectors.toList()))
            .orElseThrow(Exception::new);

    */

}
