package com.javatechie.crud.example.service.Impl;

import com.javatechie.crud.example.dto.LocalidadConsultaDTO;
import com.javatechie.crud.example.dto.LocalidadDTO;
import com.javatechie.crud.example.service.interfaz.LocalidadService;
import com.javatechie.crud.example.utils.complete.CompleteCamposLocalidad;
import com.javatechie.crud.example.utils.complete.CompleteCamposUsuarios;
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
import java.util.stream.Collectors;

@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad, Integer> implements LocalidadService {

    @Autowired
    private LocalidadRepository localidadRepository;

    @Autowired
    private MapperLocalidadesDTO mapperLocalidadesDTO;
    @Autowired
    private MetodosLocalidadUtils metodosLocalidadUtils;

    @Autowired
    private CompleteCamposLocalidad completeCampos;

    public LocalidadServiceImpl(InterfaceBaseRepository<Localidad, Integer> interfaceBaseRepository) {
        super(interfaceBaseRepository);
    }

    /**
     * Lista las loclaidades alfabeticamente.
     *
     * @param provinciaId
     * @return
     * @throws Exception
     */

    @Override
    public List<String> listLocalidades(Integer provinciaId) throws Exception {
        try {
            List<Localidad> provincias = localidadRepository.findAllForProvincia(provinciaId);
            return provincias.stream()
                    .filter(provincia -> provincia.getFechaBaja() == null)
                    .sorted(Comparator.comparing(e -> e.getDescripcionLocalidad()))
                    .map(e -> e.getDescripcionLocalidad())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
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
                Localidad localidadInactive = findById(id);
                completeCampos.localidadCamposBaja(localidadInactive);
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
            Localidad localidad = findById(id);
            return localidad.getDescripcionLocalidad();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

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
            localidadRepository.findAll().stream().forEach(localidad -> {
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
