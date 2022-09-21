package com.javatechie.crud.example.service.Impl.process;

import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.repository.UsuarioBaseRepository;
import com.javatechie.crud.example.service.interfaz.ProcessService;
import com.javatechie.crud.example.service.interfaz.UsuarioService;
import com.javatechie.crud.example.utils.verificar.impl.VerificarUsuarioEnBaseImpl;
import org.springframework.stereotype.Service;

@Service
public class UsuarioProcessServiceImpl
        extends VerificarUsuarioEnBaseImpl<Usuario, Integer> implements ProcessService<Usuario, Integer> {

    private final static String EXIST = "Este legajo ya existe!";
    private UsuarioService usuarioService;


    public UsuarioProcessServiceImpl(UsuarioBaseRepository maquinistaBaseRepository, UsuarioService usuarioService) {
        super(maquinistaBaseRepository);
        this.usuarioService = usuarioService;
    }

    @Override
    public Usuario crear(Usuario usuario, Integer adminId) throws Exception {
        try {
            if (buscarLegajo(usuario.getLegajo())) throw new Exception(EXIST);
            return usuarioService.crearUsuario(usuario, adminId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario actualizar(Integer id, Usuario usuario, Integer adminId) throws Exception {
        try {
            return usuarioService.updateUser(id, usuario, adminId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean baja(Integer id, Integer adminId) throws Exception {
        try {
            return usuarioService.bajaUsuario(id, adminId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario buscarPorId(Integer id) throws Exception {
        try {
            return usuarioService.getUsuario(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}


