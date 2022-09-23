package com.javatechie.crud.example.service.Impl.busqueda;

import com.javatechie.crud.example.dto.LocalidadDTO;
import com.javatechie.crud.example.dto.MaquinistaDTO;
import com.javatechie.crud.example.dto.ObraDTO;
import com.javatechie.crud.example.dto.UsuarioDTO;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.service.interfaz.*;
import com.javatechie.crud.example.utils.mapperDto.MapperLocalidadesDTO;
import com.javatechie.crud.example.utils.mapperDto.MapperMaquinistasDTO;
import com.javatechie.crud.example.utils.mapperDto.MapperObrasDTO;
import com.javatechie.crud.example.utils.mapperDto.MapperUsuariosDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusquedaImpl implements Busqueda {
    private UsuarioService usuarioService;
    private ObraService obraService;
    private LocalidadService localidadService;
    private MaquinistaService maquinistaService;

    public BusquedaImpl(UsuarioService usuarioService, ObraService obraService, LocalidadService localidadService,
                        MaquinistaService maquinistaService) {
        this.usuarioService = usuarioService;
        this.obraService = obraService;
        this.localidadService = localidadService;
        this.maquinistaService = maquinistaService;
    }

    @Override
    public List<UsuarioDTO> ListarBusquedaUniversal(Pageable pageable, Usuario entity) throws Exception {
        try {
            return MapperUsuariosDTO.mapperDtoUsuarios(usuarioService.listarBusquedaUniversal(pageable, (Usuario) entity));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UsuarioDTO> buscarPorNombre(Pageable pageable, String nombre) throws Exception {
        try {
            return MapperUsuariosDTO.mapperDtoUsuarios(usuarioService.listarPorNombre(nombre, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<MaquinistaDTO> buscarMaquinistaPorNombre(Pageable pageable, String nombre) throws Exception {
        return null;
    }

    @Override
    public List<LocalidadDTO> buscarLocalidadPorNombre(Pageable pageable, String nombre) throws Exception {
        try {
            return MapperLocalidadesDTO.mapperDtoLocalidades(localidadService.listarLocalidadesPorNombre(nombre, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UsuarioDTO> buscarPorApellido(Pageable pageable, String apellido) throws Exception {
        try {
            return MapperUsuariosDTO.mapperDtoUsuarios(usuarioService.listarPorApellido(apellido, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<MaquinistaDTO> buscarMaquinistaPorApellido(Pageable pageable, String apellido) throws Exception {
        try {
            return MapperMaquinistasDTO.mapperDtoMaquinistas(maquinistaService.getByApellido(pageable, apellido));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UsuarioDTO> buscarPorLegajo(Pageable pageable, Integer legajo) throws Exception {
        try {
            return MapperUsuariosDTO.mapperDtoUsuarios(usuarioService.buscarPorLegajo(pageable, legajo));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public MaquinistaDTO buscarMaquinistaPorLegajo(Integer legajo) throws Exception {
        try {
            return MapperMaquinistasDTO.mapper(maquinistaService.getByLegajo(legajo));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<UsuarioDTO> buscarPorCargo(Pageable pageable, String cargo) throws Exception {
        try {
            return MapperUsuariosDTO.mapperDtoUsuarios(usuarioService.listarPorCargo(cargo, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UsuarioDTO buscarPorUserName(String userName) throws Exception {
        try {
            return MapperUsuariosDTO.mapperDtoUsuario(usuarioService.getPorLogin(userName));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ObraDTO> buscarObraPorJefe(Pageable pageable, String jefe) throws Exception {
        try {
            return MapperObrasDTO.mapperDtoObras(obraService.listJefe(jefe, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ObraDTO> buscarObraPorGerente(Pageable pageable, String gerente) throws Exception {
        try {
            return MapperObrasDTO.mapperDtoObras(obraService.listGerente(gerente, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ObraDTO> buscarObraPorAdministrativo(Pageable pageable, String administrativo) throws Exception {
        try {
            return MapperObrasDTO.mapperDtoObras(obraService.listAdministrativo(administrativo, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ObraDTO> buscarObraPorLocalidad(Pageable pageable, String localidad) throws Exception {
        try {
            return MapperObrasDTO.mapperDtoObras(obraService.listLocalidad(localidad, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ObraDTO> buscarObraPorProvincia(Pageable pageable, String provincia) throws Exception {
        try {
            return MapperObrasDTO.mapperDtoObras(obraService.listProvincia(provincia, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ObraDTO> buscarObraPorDescripcion(Pageable pageable, String descripcion) throws Exception {
        try {
            return MapperObrasDTO.mapperDtoObras(obraService.listDescripcion(descripcion, pageable));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
