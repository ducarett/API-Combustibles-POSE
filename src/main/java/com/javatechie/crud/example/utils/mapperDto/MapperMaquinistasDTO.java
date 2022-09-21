package com.javatechie.crud.example.utils.mapperDto;

import com.javatechie.crud.example.dto.MaquinistaDTO;
import com.javatechie.crud.example.entity.Maquinista;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperMaquinistasDTO {

    public static List<MaquinistaDTO> mapperDtoMaquinistas(Page<Maquinista> entities) throws Exception {
        try {
            List<MaquinistaDTO> entitiesDto = new ArrayList<>();
            entities.stream().forEach(entity -> {
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

    public static MaquinistaDTO mapper(Maquinista entity) throws Exception {
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
