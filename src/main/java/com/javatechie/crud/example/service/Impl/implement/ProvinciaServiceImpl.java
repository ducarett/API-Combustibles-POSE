package com.javatechie.crud.example.service.Impl.implement;

import com.javatechie.crud.example.entity.Provincia;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.repository.ProvinciaRepository;
import com.javatechie.crud.example.service.Impl.abstractClass.BaseServiceImpl;
import com.javatechie.crud.example.service.interfaz.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProvinciaServiceImpl extends BaseServiceImpl<Provincia, Integer> implements ProvinciaService {
    @Autowired
    private ProvinciaRepository provinciaRepository;

    public ProvinciaServiceImpl(InterfaceBaseRepository<Provincia, Integer> interfaceBaseRepository) {
        super(interfaceBaseRepository);
    }

    @Override
    public List<Provincia> listaProvincias() throws Exception {
           return Optional
                   .ofNullable(provinciaRepository.findAll().stream()
                           .sorted(Comparator.comparing(prov -> prov.getDescriptionProvincia()))
                           .collect(Collectors.toList()))
                           .orElseThrow(Exception::new);
    }
}
