package com.javatechie.crud.example.service.Impl.implement;

import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.service.Impl.abstractClass.BaseServiceImpl;
import com.javatechie.crud.example.service.interfaz.ObraService;
import com.javatechie.crud.example.utils.complete.impl.CompleteCamposObras;
import com.javatechie.crud.example.entity.Obra;
import com.javatechie.crud.example.repository.ObraRepository;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ObraServiceImpl extends BaseServiceImpl<Obra, Integer> implements ObraService {

    private final static String NO_EXIST = "esta obra no existe";
    private ObraRepository obraRepository;
    private UsuarioRepository usuarioRepository;
    private CompleteCamposObras completeDatos;

    public ObraServiceImpl(InterfaceBaseRepository<Obra, Integer> interfaceBaseRepository,
                           ObraRepository obraRepository, UsuarioRepository usuarioRepository,
                           CompleteCamposObras completeDatos) {
        super(interfaceBaseRepository);
        this.obraRepository = obraRepository;
        this.usuarioRepository = usuarioRepository;
        this.completeDatos = completeDatos;
    }

    @Override
    public Obra crearObra(Obra entity, Integer adminId) throws Exception {
        try {
            return save(completeDatos.alta(entity, adminId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Obra editarObra(Integer id, Obra obra, Integer adminId) throws Exception {
        try {
            return update(id, completeDatos.modificar(obra, adminId));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean bajaObra(Integer id, Integer adminId) throws Exception {
        try {
            Obra obra = getById(id);
            if (Objects.isNull(obra)) throw new Exception(NO_EXIST);
            completeDatos.baja(obra, adminId);
            update(id, obra);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Obra getEntity(Integer id) throws Exception {
        try {
            return getEntity(getById(id));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Page<Obra> listAllObras(Pageable pageable) throws Exception {
        try {
            return listGeneric(obraRepository.findAll(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Page<Obra> listActivos(Pageable pageable) throws Exception {
        try {
            return listGeneric(obraRepository.findByFechaBajaIsNullAndHoraBajaIsNull(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Page<Obra> listInactivos(Pageable pageable) throws Exception {
        try {
            return listGeneric(obraRepository.findByFechaBajaIsNotNullAndUsuarioBajaIsNotNull(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Obra> listProvincia(String provincia, Pageable pageable) throws Exception {
        try {
            return listGeneric(obraRepository.findByFechaBajaIsNotNullAndUsuarioBajaIsNotNull(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Obra> listDescripcion(String descripcion, Pageable pageable) throws Exception {
        try {
            return listGeneric(obraRepository.findByFechaBajaIsNotNullAndUsuarioBajaIsNotNull(pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Obra> listLocalidad(String localidad, Pageable pageable) throws Exception {
        try {
            return listGeneric(obraRepository.findByLocalidad(localidad, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Obra> listGerente(String nom_ape, Pageable pageable) throws Exception {
        try {
            return listGeneric(obraRepository.findByGerente(usuarioRepository.findUserIsGerente(nom_ape).getUsuarioId(), pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Obra> listJefe(String nom_ape, Pageable pageable) throws Exception {
        try {
            return listGeneric(obraRepository.findByJefe(usuarioRepository.findUserIsJefe(nom_ape).getUsuarioId(), pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Obra> listAdministrativo(String nom_ape, Pageable pageable) throws Exception {
        try {
            return listGeneric(obraRepository.findByAdministrativo(usuarioRepository.findUserIsAdmin(nom_ape).getUsuarioId(), pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
