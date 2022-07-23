package com.javatechie.crud.example.service.Impl;

import com.javatechie.crud.example.entity.Provincia;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.repository.ProvinciaRepository;
import com.javatechie.crud.example.service.interfaz.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinciaServiceImpl extends BaseServiceImpl<Provincia, Integer> implements ProvinciaService {
    @Autowired
    private ProvinciaRepository provinciaRepository;

    public ProvinciaServiceImpl(InterfaceBaseRepository<Provincia, Integer> interfaceBaseRepository) {
        super(interfaceBaseRepository);
    }

    @Override
    public List<String> listaProvincias() throws Exception {
        try {
            List<Provincia> provincias = provinciaRepository.findAll();
            return provincias.stream()
                    .sorted(Comparator.comparing(e -> e.getDescriptionProvincia()))
                    .map(e -> e.getDescriptionProvincia())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
