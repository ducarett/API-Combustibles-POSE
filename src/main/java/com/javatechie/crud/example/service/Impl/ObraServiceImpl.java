package com.javatechie.crud.example.service.Impl;

import com.javatechie.crud.example.service.interfaz.ObraService;
import com.javatechie.crud.example.utils.complete.impl.CompleteCamposObras;
import com.javatechie.crud.example.dto.ObraDTO;
import com.javatechie.crud.example.entity.Obra;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.repository.ObraRepository;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
            Obra obra = getById(id);
            if (Objects.isNull(obra)) throw new Exception(NO_EXIST);
            return obra;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Page<Obra> listAllObras(Pageable pageable) throws Exception {
        try {
            return obraRepository.findAll(pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public Page<Obra> listActivos(Pageable pageable) throws Exception {
        try {
            return obraRepository.findByFechaBajaIsNullAndHoraBajaIsNull(pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public Page<Obra> listInactivos(Pageable pageable) throws Exception {
        try {
            return obraRepository.findByFechaBajaIsNotNullAndUsuarioBajaIsNotNull(pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

   /* @Override
    public List<ObraDTO> listProvincia(String provincia, Pageable pageable) throws Exception {
        try {
            return mapperObrasDTO.mapperDtoObrasActivas(obraRepository.findByProvincia(provincia, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * servicio lista obras segun la descripcion.
     *
     * @param descripcion
     * @return
     * @throws Exception
     */
  /*  @Override
    public List<ObraDTO> listDescripcion(String descripcion, Pageable pageable) throws Exception {
        try {
            return mapperObrasDTO.mapperDtoObrasActivas(obraRepository.findByDescripcion(descripcion, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * servicio lista obras segun la localidad.
     *
     * @param localidad
     * @return
     * @throws Exception
     */
  /*  @Override
    public List<ObraDTO> listLocalidad(String localidad, Pageable pageable) throws Exception {
        try {
            return mapperObrasDTO.mapperDtoObrasActivas(obraRepository.findByLocalidad(localidad, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * servicio lista obras segun el gerente.
     *
     * @param nom_ape
     * @return
     * @throws Exception
     */
/*    @Override
    public List<ObraDTO> listGerente(String nom_ape, Pageable pageable) throws Exception {
        try {
            Usuario gerente = usuarioRepository.findUserIsGerente(nom_ape);
            return mapperObrasDTO.mapperDtoObrasActivas(obraRepository.findByGerente(gerente.getUsuarioId(), pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * servicio lista obras segun el jefe.
     *
     * @param nom_ape
     * @return
     * @throws Exception
     */

/*    @Override
    public List<ObraDTO> listJefe(String nom_ape, Pageable pageable) throws Exception {
        try {
            Usuario jefe = usuarioRepository.findUserIsJefe(nom_ape);
            return mapperObrasDTO.mapperDtoObrasActivas(obraRepository.findByJefe(jefe.getUsuarioId(), pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * servico lista obras segun el administrativo.
     *
     * @param nom_ape
     * @return
     * @throws Exception
     */
/*    @Override
    public List<ObraDTO> listAdministrativo(String nom_ape, Pageable pageable) throws Exception {
        try {
            Usuario admin = usuarioRepository.findUserIsAdmin(nom_ape);
            return mapperObrasDTO.mapperDtoObrasActivas(obraRepository.findByAdministrativo(admin.getUsuarioId(), pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
*/

}
