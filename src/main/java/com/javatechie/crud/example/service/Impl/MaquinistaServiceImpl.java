package com.javatechie.crud.example.service.Impl;

import com.javatechie.crud.example.service.interfaz.MaquinistaService;
import com.javatechie.crud.example.utils.complete.impl.CompletarCamposMaquinista;
import com.javatechie.crud.example.entity.Maquinista;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.repository.MaquinistaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MaquinistaServiceImpl extends BaseServiceImpl<Maquinista, Integer> implements MaquinistaService {

    private final static String NO_EXIST = "Este maquinista no existe!";

    private MaquinistaRepository maquinistaRepository;

    private CompletarCamposMaquinista completeCampos;

    public MaquinistaServiceImpl(InterfaceBaseRepository<Maquinista, Integer> interfaceBaseRepository,
                                 MaquinistaRepository maquinistaRepository,
                                 CompletarCamposMaquinista completeCampos) {
        super(interfaceBaseRepository);
        this.maquinistaRepository = maquinistaRepository;
        this.completeCampos = completeCampos;
    }

    /**
     * inactiva un usuarioo mediante el ID.
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean bajaMaquinista(Integer id, Integer adminId) throws Exception {
        try {
            Maquinista maquinista = getById(id);
            if (Objects.isNull(maquinista)) throw new Exception(NO_EXIST);
            completeCampos.baja(maquinista, adminId);
            update(id, maquinista);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Maquinista crearMaquinista(Maquinista maquinista, Integer adminId) throws Exception {
        try {
            return save(completeCampos.alta(maquinista, adminId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Maquinista actualizarMaquinista(Integer id, Maquinista maquinista, Integer adminId) throws Exception {
        try {
            completeCampos.modificar(maquinista, adminId);
            return update(id, maquinista);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Maquinista getEntity(Integer id) throws Exception {
        try {
            Maquinista maquinista = getById(id);
            if (Objects.isNull(maquinista)) throw new Exception(NO_EXIST);
            return maquinista;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Maquinista> listActivos(Pageable pageable) throws Exception {
        try {
            Page<Maquinista> maquinistas = maquinistaRepository.findByFechaBajaIsNullAndHoraBajaIsNull(pageable);
            if (maquinistas.isEmpty()) throw new Exception(NO_EXIST);
            return maquinistas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Maquinista> listInactivos(Pageable pageable) throws Exception {
        try {
            Page<Maquinista> maquinistas = maquinistaRepository.findByFechaBajaIsNotNullAndHoraBajaIsNotNull(pageable);
            if (maquinistas.isEmpty()) throw new Exception(NO_EXIST);
            return maquinistas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Maquinista> listAllMaquinistas(Pageable pageable) throws Exception {
        try {
            Page<Maquinista> maquinistas = maquinistaRepository.findAll(pageable);
            if (maquinistas.isEmpty()) throw new Exception(NO_EXIST);
            return maquinistas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Maquinista getByLegajo(Integer legajo) throws Exception {
        try {
            Maquinista maquinista = maquinistaRepository.findByLegajo(legajo);
            if (Objects.isNull(maquinista)) throw new Exception(NO_EXIST);
            return maquinista;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public Maquinista getByNombre(String nombre) throws Exception {
        try {
            Maquinista maquinista = maquinistaRepository.findByNombre(nombre);
            if (Objects.isNull(maquinista)) throw new Exception(NO_EXIST);
            return maquinista;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Maquinista getByApellido(String apellido) throws Exception {
        try {
            Maquinista maquinista = maquinistaRepository.findByApellido(apellido);
            if (Objects.isNull(maquinista)) throw new Exception(NO_EXIST);
            return maquinista;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}