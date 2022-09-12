package com.javatechie.crud.example.service.Impl.process;

import com.javatechie.crud.example.entity.Obra;
import com.javatechie.crud.example.repository.ObraBaseRepository;
import com.javatechie.crud.example.service.interfaz.ObraService;
import com.javatechie.crud.example.service.interfaz.ProcessService;
import com.javatechie.crud.example.utils.verificar.impl.VerificarObraEnBaseImpl;
import org.springframework.stereotype.Service;

@Service
public class ObraProcessServiceImpl extends VerificarObraEnBaseImpl<Obra, Integer> implements ProcessService<Obra, Integer> {

    private final static String EXIST = "el codigo de obra ya existe!";
    private ObraService obraService;

    public ObraProcessServiceImpl(ObraBaseRepository obraBaseRepository, ObraService obraService) {
        super(obraBaseRepository);
        this.obraService = obraService;
    }

    @Override
    public Obra crear(Obra obra, Integer adminId) throws Exception {
        try {
            if (buscarCodigo(obra.getCodigoObra())) throw new Exception(EXIST);
            return obraService.crearObra(obra, adminId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public Obra actualizar(Integer id, Obra obra, Integer adminId) throws Exception {
        try {
            if (compararCodigos(id, obra)) {
                if (buscarCodigo(obra.getCodigoObra())) throw new Exception(EXIST);
            }
            return obraService.editarObra(id, obra, adminId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean baja(Integer id, Integer adminId) throws Exception {
        try {
            return obraService.bajaObra(id, adminId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Obra buscarPorId(Integer id) throws Exception {
        try {
            return obraService.getEntity(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
