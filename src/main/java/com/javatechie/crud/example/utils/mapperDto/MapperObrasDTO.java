package com.javatechie.crud.example.utils.mapperDto;

import com.javatechie.crud.example.dto.ObraDTO;
import com.javatechie.crud.example.entity.Obra;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperObrasDTO {

    public static List<ObraDTO> mapperDtoObras(Page<Obra> entities) throws Exception {
        try {
            List<ObraDTO> entitiesDto = new ArrayList<>();
            for (Obra auxObras : entities) {
                entitiesDto.add(setDtoObras(auxObras));
            }
            return entitiesDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static ObraDTO setDtoObras(Obra entity) throws Exception {
        try {
            ObraDTO dto = ObraDTO.builder()
                    .obraId(entity.getObraId())
                    .codigoObra(entity.getCodigoObra())
                    .descripcion(entity.getDescripcion().toUpperCase())
                    .Provincia(entity.getProvincia().getDescriptionProvincia().toUpperCase())
                    .Localidad(entity.getLocalidad().getDescripcionLocalidad().toUpperCase())
                    .gerente(entity.getGerente().getApellido().toUpperCase().concat(" " + entity.getGerente().getNombre().toUpperCase()))
                    .jefe(entity.getJefe().getApellido().toUpperCase().concat(" " + entity.getJefe().getNombre().toUpperCase()))
                    .administrativo(entity.getAdmin().getApellido().toUpperCase().concat(" " + entity.getAdmin().getNombre().toUpperCase()))
                    .build();
            return dto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
