package com.javatechie.crud.example.service.Impl;

import com.javatechie.crud.example.service.interfaz.MaquinistaService;
import com.javatechie.crud.example.utils.complete.CompleteCamposMaquinistas;
import com.javatechie.crud.example.utils.complete.CompleteCamposObras;
import com.javatechie.crud.example.utils.complete.CompleteCamposUsuarios;
import com.javatechie.crud.example.dto.MaquinistaActivoDTO;
import com.javatechie.crud.example.dto.MaquinistaConsultaDTO;
import com.javatechie.crud.example.entity.Maquinista;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.repository.MaquinistaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaquinistaServiceImpl extends BaseServiceImpl<Maquinista, Integer> implements MaquinistaService {
    @Autowired
    private MaquinistaRepository maquinistaRepository;

    @Autowired
    private CompleteCamposMaquinistas completeCampos;

    public MaquinistaServiceImpl(InterfaceBaseRepository<Maquinista, Integer> interfaceBaseRepository) {
        super(interfaceBaseRepository);
    }

    /**
     * inactiva un usuarioo mediante el ID.
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean bajaMaquinista(Integer id) throws Exception {
        try {
            if (interfaceBaseRepository.existsById(id)) {
                Maquinista maquinistaInactive = findById(id);
                completeCampos.maquinistaCamposBaja(maquinistaInactive);
                interfaceBaseRepository.save(maquinistaInactive);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * servicio lista maquinistas activos.
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Maquinista> listActivos() throws Exception {
        try {
            List<Maquinista> entities = maquinistaRepository.findByFechaBajaIsNullAndHoraBajaIsNull();
            return entities;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * servicio retorna un maquinista por ID.
     *
     * @param legajo
     * @return
     * @throws Exception
     */
    @Override
    public MaquinistaActivoDTO buscarPorLegajo(Integer legajo) throws Exception {
        try {
            Maquinista maquinista = maquinistaRepository.findByLegajo(legajo);
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(maquinista, MaquinistaActivoDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * servicio retorna un maquinista por nombre.
     *
     * @param nombre
     * @return
     * @throws Exception
     */
    @Override
    public MaquinistaActivoDTO buscarPorNombre(String nombre) throws Exception {
        try {
            Maquinista maquinista = maquinistaRepository.findByNombre(nombre);
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(maquinista, MaquinistaActivoDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * servicio retorna un mquinista por apellido.
     *
     * @param apellido
     * @return
     * @throws Exception
     */
    @Override
    public MaquinistaActivoDTO buscarPorApellido(String apellido) throws Exception {
        try {
            Maquinista maquinista = maquinistaRepository.findByApellido(apellido);
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(maquinista, MaquinistaActivoDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}