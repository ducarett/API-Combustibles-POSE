package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.dto.LocalidadDTO;
import com.javatechie.crud.example.dto.MaquinistaDTO;
import com.javatechie.crud.example.dto.ObraDTO;
import com.javatechie.crud.example.dto.UsuarioDTO;
import com.javatechie.crud.example.entity.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface Busqueda {

    List<UsuarioDTO> ListarBusquedaUniversal(Pageable pageable, Usuario entity) throws Exception;

    List<UsuarioDTO> buscarPorNombre(Pageable pageable, String nombre) throws Exception;

    List<MaquinistaDTO> buscarMaquinistaPorNombre(Pageable pageable, String nombre) throws Exception;

    List<LocalidadDTO> buscarLocalidadPorNombre(Pageable pageable, String nombre) throws Exception;

    List<UsuarioDTO> buscarPorApellido(Pageable pageable, String apellido) throws Exception;

    List<MaquinistaDTO> buscarMaquinistaPorApellido(Pageable pageable, String apellido) throws Exception;

    UsuarioDTO buscarPorLegajo(Integer legajo) throws Exception;

    MaquinistaDTO buscarMaquinistaPorLegajo(Integer legajo) throws Exception;

    List<UsuarioDTO> buscarPorCargo(Pageable pageable, String cargo) throws Exception;

    UsuarioDTO buscarPorUserName(String userName) throws Exception;

    List<ObraDTO> buscarObraPorJefe(Pageable pageable, String jefe) throws Exception;

    List<ObraDTO> buscarObraPorGerente(Pageable pageable, String gerente) throws Exception;

    List<ObraDTO> buscarObraPorAdministrativo(Pageable pageable, String administrativo) throws Exception;

    List<ObraDTO> buscarObraPorLocalidad(Pageable pageable, String localidad) throws Exception;

    List<ObraDTO> buscarObraPorProvincia(Pageable pageable, String provincia) throws Exception;

    List<ObraDTO> buscarObraPorDescripcion(Pageable pageable, String descripcion) throws Exception;
}
