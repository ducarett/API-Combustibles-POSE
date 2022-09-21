package com.javatechie.crud.example.utils.mapperDto;

import com.javatechie.crud.example.dto.*;
import com.javatechie.crud.example.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MapperUsuariosDTO {

    public static UserLoginDto mapperLoginDto(Usuario usuario) throws Exception {
        try {
            ModelMapper model = new ModelMapper();
            return model.map(usuario, UserLoginDto.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static UsuarioDTO mapperDtoUsuario(Usuario usuario) throws Exception {
        try {
            return mapper(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private static UsuarioDTO mapper(Usuario entity) throws Exception {
        try {
            UsuarioDTO dto = UsuarioDTO.builder()
                    .id(entity.getUsuarioId())
                    .nombre(entity.getNombre())
                    .apellido(entity.getApellido())
                    .login(entity.getLogin())
                    .legajo(entity.getLegajo())
                    .descripcionCargo(entity.getCargo().getDescripcionCargo())
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

    public static List<UsuarioDTO> mapperDtoUsuarios(Page<Usuario> entities) throws Exception {
        try {
            List<UsuarioDTO> entitiesDto = new ArrayList<>();
            for (Usuario auxUsuario : entities) {
                entitiesDto.add(mapper(auxUsuario));
            }
            return entitiesDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
