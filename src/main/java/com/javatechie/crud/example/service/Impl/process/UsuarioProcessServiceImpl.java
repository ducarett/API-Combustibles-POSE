package com.javatechie.crud.example.service.Impl.process;

import com.javatechie.crud.example.entity.Maquinista;
import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.repository.MaquinistaBaseRepository;
import com.javatechie.crud.example.repository.UsuarioBaseRepository;
import com.javatechie.crud.example.service.interfaz.MaquinistaService;
import com.javatechie.crud.example.service.interfaz.ProcessService;
import com.javatechie.crud.example.service.interfaz.UsuarioService;
import com.javatechie.crud.example.utils.verificar.impl.VerificarMaquinistaEnBaseImpl;
import com.javatechie.crud.example.utils.verificar.impl.VerificarUsuarioEnBaseImpl;
import org.springframework.stereotype.Service;

@Service
public class UsuarioProcessServiceImpl
        extends VerificarUsuarioEnBaseImpl<Usuario, Integer> implements ProcessService<Usuario, Integer> {

    private final static String EXIST = "Este legajo ya existe!";
    private UsuarioService usuarioService;

    public UsuarioProcessServiceImpl(UsuarioBaseRepository maquinistaBaseRepository,
                                     UsuarioService usuarioService) {
        super(maquinistaBaseRepository);
        this.usuarioService = usuarioService;
    }

    @Override
    public Usuario crear(Usuario maquinista, Integer adminId) throws Exception {
        return null;
    }

    @Override
    public Usuario actualizar(Integer integer, Usuario Maquinista, Integer adminId) throws Exception {
        return null;
    }

    @Override
    public Boolean baja(Integer integer, Integer adminId) throws Exception {
        return null;
    }

    @Override
    public Usuario buscarPorId(Integer integer) throws Exception {
        return null;
    }
}

