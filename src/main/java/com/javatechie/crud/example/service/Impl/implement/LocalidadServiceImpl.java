package com.javatechie.crud.example.service.Impl.implement;

import com.javatechie.crud.example.service.Impl.abstractClass.BaseServiceImpl;
import com.javatechie.crud.example.service.interfaz.LocalidadService;
import com.javatechie.crud.example.utils.complete.impl.CompleteCamposLocalidad;
import com.javatechie.crud.example.entity.Localidad;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.repository.LocalidadRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad, Integer> implements LocalidadService {
    private final static String NO_EXIST = "No se encontro ninguna localidad!";
    private final static String ERROR = "Error al ejecutar un proceso sobre la entidad localidad";
    private LocalidadRepository localidadRepository;
    private CompleteCamposLocalidad completeCamposLocalidad;

    public LocalidadServiceImpl(InterfaceBaseRepository<Localidad, Integer> interfaceBaseRepository,
                                LocalidadRepository localidadRepository,
                                CompleteCamposLocalidad completeCamposLocalidad) {
        super(interfaceBaseRepository);
        this.localidadRepository = localidadRepository;
        this.completeCamposLocalidad = completeCamposLocalidad;
    }


    public Localidad crearLocalidad(Localidad localidad, Integer adminId) throws Exception {
        try {
            return save(completeCamposLocalidad.alta(localidad, adminId));
        } catch (Exception e) {
            throw new Exception(ERROR);
        }
    }

    public Localidad editarLocalidad(Integer id, Localidad localidad, Integer adminId) throws Exception {
        try {
            return update(id, completeCamposLocalidad.modificar(localidad, adminId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean bajaLocalidad(Integer id, Integer adminId) throws Exception {
        try {
            Localidad localidad = getById(id);
            if (Objects.isNull(localidad)) throw new Exception(NO_EXIST);
            completeCamposLocalidad.baja(localidad, adminId);
            update(id, localidad);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Localidad getPorId(Integer id) throws Exception {
        try {
            return getEntity(getById(id));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Localidad> listarLocalidadesPorProvId(Integer provinciaId, Pageable pageable) throws Exception {
        try {
            return listGeneric(localidadRepository.findAllForProvincia(provinciaId, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Localidad> listarLocalidadesPorNombre(String nombre, Pageable pageable) throws Exception {
        try {
            return listGeneric(localidadRepository.findListNombre(nombre, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Page<Localidad> listAllObras(Pageable pageable) throws Exception {
        try {
            return listGeneric(localidadRepository.findAll(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Page<Localidad> listInactivos(Pageable pageable) throws Exception {
        try {
            return listGeneric(localidadRepository.findByFechaBajaIsNotNullAndHoraBajaIsNotNull(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Page<Localidad> listActivos(Pageable pageable) throws Exception {
        try {
            return listGeneric(localidadRepository.findByFechaBajaIsNullAndHoraBajaIsNull(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
