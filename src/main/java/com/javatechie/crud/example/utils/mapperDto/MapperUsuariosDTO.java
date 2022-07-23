package com.javatechie.crud.example.utils.mapperDto;

import com.javatechie.crud.example.dto.*;
import com.javatechie.crud.example.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MapperUsuariosDTO {

    ModelMapper modelMapper = new ModelMapper();

    /**
     * setea todo un dto con los datos de usuarios inactivos.
     *
     * @param entities
     * @return
     * @throws Exception
     */
    public List<UserInactiveDTO> mapperDtoUsuarioInactivo(List<Usuario> entities) throws Exception {
        try {
            List<UserInactiveDTO> entitiesDto = new ArrayList<>();
            for (Usuario auxUsuario : entities) {
                entitiesDto.add(modelMapper.map(auxUsuario, UserInactiveDTO.class));
            }
            return entitiesDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * setea un dto determinando si es un usuario activo o inactivo.
     *
     * @param entity
     * @return
     * @throws Exception
     */
    private UserConsultaDTO mapperActivoinactivo(Usuario entity) throws Exception {
        try {
            UserConsultaDTO dto = UserConsultaDTO.builder()
                    .nombre(entity.getNombre())
                    .apellido(entity.getApellido())
                    .login(entity.getLogin())
                    .legajo(entity.getLegajo())
                    .cargo(entity.getCargo().getDescripcionCargo())
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

    /**
     * mappea un dto con campo activo.
     *
     * @param entities
     * @return
     * @throws Exception
     */

    @Transactional
    public List<UserConsultaDTO> mapperDtoConsultaUsuarios(List<Usuario> entities) throws Exception {
        try {
            List<UserConsultaDTO> entitiesDto = new ArrayList<>();
            for (Usuario auxUsuario : entities) {
                entitiesDto.add(mapperActivoinactivo(auxUsuario));
            }
            return entitiesDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<UserDTO> mapperDtoUsuarioActivo(List<Usuario> entities) throws Exception {
        try {
            List<UserDTO> entitiesDto = new ArrayList<>();
            for (Usuario auxUsuario : entities) {
                entitiesDto.add(modelMapper.map(auxUsuario, UserDTO.class));
            }
            return entitiesDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * retorna un DTO de usuario por login.
     *
     * @param user
     * @return
     * @throws Exception
     */
    public UserDTO buscarPorLogin(Usuario user) throws Exception {
        try {
            return modelMapper.map(user, UserDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
