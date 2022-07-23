package com.javatechie.crud.example.utils.mapperDto;

import com.javatechie.crud.example.dto.ObrasActivasDTO;
import com.javatechie.crud.example.entity.Obra;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperObrasDTO {

    /**
     * setea el dto de las obras activas.
     *
     * @param entities
     * @return
     * @throws Exception
     */
    public List<ObrasActivasDTO> mapperDtoObrasActivas(List<Obra> entities) throws Exception {
        try {
            List<ObrasActivasDTO> entitiesDto = new ArrayList<>();
            for (Obra auxObras : entities) {
                entitiesDto.add(setDtoObrasActivas(auxObras));
            }
            return entitiesDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * setea los campos del dto de obras activas.
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public ObrasActivasDTO setDtoObrasActivas(Obra entity) throws Exception {
        try {
            ObrasActivasDTO dto = ObrasActivasDTO.builder() // ver si anda
                    .obraId(entity.getObraId())
                    .descripcion(entity.getDescripcion())
                    .Provincia(entity.getProvincia().getDescriptionProvincia())
                    .Localidad(entity.getLocalidad().getDescripcionLocalidad())
                    .gerente(entity.getGerente().getApellido().concat(" " + entity.getGerente().getApellido()))
                    .jefe(entity.getJefe().getApellido().concat(" " + entity.getJefe().getApellido()))
                    .administrativo(entity.getAdmin().getApellido().concat(" " + entity.getAdmin().getApellido()))
                    .build();
            return dto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
 /*int id = entity.getGerente().getUsuarioId();
            Optional<Usuario> gerente = usuarioRepository.findById(id);
            dto.setGerente(gerente.get().getApellido().concat(" " + gerente.get().getNombre()));
            id = entity.getJefe().getUsuarioId();
            Optional<Usuario> jefe = usuarioRepository.findById(id);
            dto.setJefe(jefe.get().getApellido().concat(" " + jefe.get().getNombre()));
            id = entity.getAdmin().getUsuarioId();
            Optional<Usuario> administrativo = usuarioRepository.findById(id);
            dto.setAdministrativo(administrativo.get().getApellido().concat(" " + administrativo.get().getNombre()));*/
}
