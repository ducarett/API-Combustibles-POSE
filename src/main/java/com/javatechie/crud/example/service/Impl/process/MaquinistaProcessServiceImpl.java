package com.javatechie.crud.example.service.Impl.process;

import com.javatechie.crud.example.entity.Maquinista;
import com.javatechie.crud.example.repository.MaquinistaBaseRepository;
import com.javatechie.crud.example.service.interfaz.ProcessService;
import com.javatechie.crud.example.service.interfaz.MaquinistaService;
import com.javatechie.crud.example.utils.verificar.impl.VerificarMaquinistaEnBaseImpl;
import org.springframework.stereotype.Service;

@Service
public class MaquinistaProcessServiceImpl
        extends VerificarMaquinistaEnBaseImpl<Maquinista, Integer> implements ProcessService<Maquinista, Integer> {
    private final static String EXIST = "Este legajo ya existe!";
    private MaquinistaService maquinistaService;

    public MaquinistaProcessServiceImpl(MaquinistaBaseRepository maquinistaBaseRepository,
                                        MaquinistaService maquinistaService) {
        super(maquinistaBaseRepository);
        this.maquinistaService = maquinistaService;
    }

    @Override
    public Maquinista crear(Maquinista maquinista, Integer adminID) throws Exception {
        try {
            if (buscarLegajo(maquinista.getLegajo())) throw new Exception(EXIST);
            return maquinistaService.crearMaquinista(maquinista, adminID);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Maquinista actualizar(Integer id, Maquinista maquinista, Integer adminId) throws Exception {
        try {
            if (compararLegajos(id, maquinista.getLegajo())) {
                if (buscarLegajo(maquinista.getLegajo())) throw new Exception(EXIST);
            }
            return maquinistaService.actualizarMaquinista(id, maquinista, adminId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean baja(Integer id, Integer adminId) throws Exception {
        try {
            return maquinistaService.bajaMaquinista(id, adminId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Maquinista buscarPorId(Integer id) throws Exception {
        try {
            return maquinistaService.getEntity(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

