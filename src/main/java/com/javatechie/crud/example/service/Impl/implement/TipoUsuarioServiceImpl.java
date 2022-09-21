package com.javatechie.crud.example.service.Impl.implement;


import com.javatechie.crud.example.entity.TipoUsuario;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.repository.TipoUsuarioRepository;
import com.javatechie.crud.example.service.Impl.abstractClass.BaseServiceImpl;
import com.javatechie.crud.example.service.interfaz.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

public class TipoUsuarioServiceImpl extends BaseServiceImpl<TipoUsuario, Integer> implements TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository userTypeRepository;

    public TipoUsuarioServiceImpl(InterfaceBaseRepository<TipoUsuario, Integer> interfaceBaseRepository) {
        super(interfaceBaseRepository);
    }
}
