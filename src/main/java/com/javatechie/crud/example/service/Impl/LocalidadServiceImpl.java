package com.javatechie.crud.example.service.Impl;

import com.javatechie.crud.example.dto.LocalidadConsultaDTO;
import com.javatechie.crud.example.dto.LocalidadDTO;
import com.javatechie.crud.example.service.interfaz.LocalidadService;
import com.javatechie.crud.example.utils.complete.impl.CompleteCamposLocalidad;
import com.javatechie.crud.example.entity.Localidad;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.repository.LocalidadRepository;
import com.javatechie.crud.example.utils.mapperDto.MapperLocalidadesDTO;
import com.javatechie.crud.example.utils.metodo.MetodosLocalidadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad, Integer> implements LocalidadService {
    private LocalidadRepository localidadRepository;
    private MapperLocalidadesDTO mapperLocalidadesDTO;
    private MetodosLocalidadUtils metodosLocalidadUtils;
    private CompleteCamposLocalidad completeCampos;

    public LocalidadServiceImpl(InterfaceBaseRepository<Localidad, Integer> interfaceBaseRepository,
                                LocalidadRepository localidadRepository,
                                MapperLocalidadesDTO mapperLocalidadesDTO,
                                MetodosLocalidadUtils metodosLocalidadUtils,
                                CompleteCamposLocalidad completeCampos ) {
        super(interfaceBaseRepository);
        this.localidadRepository = localidadRepository;
        this.mapperLocalidadesDTO = mapperLocalidadesDTO;
        this.metodosLocalidadUtils = metodosLocalidadUtils;
        this.completeCampos = completeCampos;
    }

    /**
     * Lista las loclaidades alfabeticamente.
     *
     * @param provinciaId
     * @return
     * @throws Exception
     */

    @Override 
    public List<LocalidadDTO> listLocalidades(Integer provinciaId) throws Exception {
        ModelMapper model = new ModelMapper();
        return Optional
                .ofNullable(localidadRepository.findAllForProvincia(provinciaId).stream()
                        .filter(localidad -> localidad.getFechaBaja() == null)
                        .sorted(Comparator.comparing(localidad -> localidad.getDescripcionLocalidad()))
                        .map(localidad -> model.map(localidad,LocalidadDTO.class))
                        .collect(Collectors.toList()))
                .orElseThrow(Exception::new);

    }

    /**
     * servicio baja de localidad.
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean bajaLocalidad(Integer id) throws Exception {
        try {
            if (interfaceBaseRepository.existsById(id)) {
                Localidad localidadInactive = getById(id);
               // completeCampos.localidadCamposBaja(localidadInactive);
                interfaceBaseRepository.save(localidadInactive);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * busqueda por ID.
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public String buscarPorId(Integer id) throws Exception {
        try {
            Localidad localidad = getById(id);
            return localidad.getDescripcionLocalidad().toUpperCase();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<LocalidadDTO> ordenarLocalidades() throws Exception {
        try {
            return metodosLocalidadUtils.ordenarProvicnciasAlfabeticamente(localidadRepository.findByFechaBajaIsNullAndHoraBajaIsNull());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<LocalidadConsultaDTO> ordenarLocalidadesActivosInactivos() throws Exception {
        try {
            List<LocalidadConsultaDTO> entities = new ArrayList<>();
            localidadRepository.findAll().forEach(localidad -> {
                try {
                    entities.add(mapperLocalidadesDTO.mapperActivoinactivo(localidad));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
