package com.javatechie.crud.example.service.Impl;

import com.javatechie.crud.example.service.interfaz.ObraService;
import com.javatechie.crud.example.utils.complete.CompleteCamposObras;
import com.javatechie.crud.example.dto.ObrasActivasDTO;
import com.javatechie.crud.example.entity.Obra;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.repository.ObraRepository;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.repository.UsuarioRepository;
import com.javatechie.crud.example.utils.mapperDto.MapperObrasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraServiceImpl extends BaseServiceImpl<Obra, Integer> implements ObraService {

    @Autowired
    private ObraRepository obraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MapperObrasDTO mapperObrasDTO;

    @Autowired
    private CompleteCamposObras completeDatos;

    public ObraServiceImpl(InterfaceBaseRepository<Obra, Integer> interfaceBaseRepository) {
        super(interfaceBaseRepository);
    }

    /**
     * retorna una obra por su id.
     *
     * @param id
     * @return
     */
    @Override
    public ObrasActivasDTO getPorObraID(Integer id) throws Exception {
        try {
            return mapperObrasDTO.setDtoObrasActivas(getObraForId(id));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * genera una lista de obras segun la provincia.
     *
     * @param provincia
     * @return
     * @throws Exception
     */
    @Override
    public List<ObrasActivasDTO> listProvincia(String provincia, Pageable pageable) throws Exception {
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
    @Override
    public List<ObrasActivasDTO> listDescripcion(String descripcion, Pageable pageable) throws Exception {
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
    @Override
    public List<ObrasActivasDTO> listLocalidad(String localidad, Pageable pageable) throws Exception {
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
    @Override
    public List<ObrasActivasDTO> listGerente(String nom_ape, Pageable pageable) throws Exception {
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

    @Override
    public List<ObrasActivasDTO> listJefe(String nom_ape, Pageable pageable) throws Exception {
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
    @Override
    public List<ObrasActivasDTO> listAdministrativo(String nom_ape, Pageable pageable) throws Exception {
        try {
            Usuario admin = usuarioRepository.findUserIsAdmin(nom_ape); //revisar
            return mapperObrasDTO.mapperDtoObrasActivas(obraRepository.findByAdministrativo(admin.getUsuarioId(), pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * servicio retorna una obra por ID
     */
    private Obra getObraForId(Integer id) {
        Optional<Obra> optional = interfaceBaseRepository.findById(id);
        return optional.get();
    }

    /**
     * verifica si el codigo no existe en ninguna obra existente y lo persiste
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Obra crearObra(Obra entity) throws Exception {
        try {
            if (!verificarCodigo(entity.getCodigoObra())) {
                return save(entity);
            } else {
                throw new Exception("El codigo de obra ya existe!");
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Obra editarObra(Integer id, Obra entity) throws Exception {
        try {
            if (verificarIgualdad(id, entity)) {
                return update(id, entity);
            } else {
                if (!verificarCodigo(entity.getCodigoObra())) {
                    return update(id, entity);
                } else {
                    throw new Exception("El codigo de obra ya existe!");
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    /**
     * servicio lista obras activas.
     *
     * @return
     * @throws Exception
     */
    public Page<Obra> listActivas(Pageable pageable) throws Exception {
        try {
            return obraRepository.findByFechaBajaIsNullAndHoraBajaIsNull(pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    private boolean verificarIgualdad(Integer id, Obra entity){
        return obraRepository.findById(id).get().getCodigoObra().equals(entity.getCodigoObra()) ? true : false;
    }
    private boolean verificarCodigo(Integer codigo) {
        return obraRepository.findByCodigoObra(codigo) != null ? true : false;
    }


    /**
     * servicio lista obras dadas de baja.
     *
     * @return
     * @throws Exception
     */
    public Page<Obra> listInactivas(Pageable pageable) throws Exception {
        try {
            return obraRepository.findByFechaBajaIsNotNullAndUsuarioBajaIsNotNull(pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * servicio de baja de obra.
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean bajaObra(Integer id) throws Exception {
        try {
            if (interfaceBaseRepository.existsById(id)) {
                Obra obraInactive = getById(id);
                completeDatos.obraCamposBaja(obraInactive);
                interfaceBaseRepository.save(obraInactive);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
