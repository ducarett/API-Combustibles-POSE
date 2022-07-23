package com.javatechie.crud.example.service.Impl;

import com.javatechie.crud.example.entity.Cargo;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.repository.CargoRepository;
import com.javatechie.crud.example.service.interfaz.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoServiceImpl extends BaseServiceImpl<Cargo, Integer> implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public CargoServiceImpl(InterfaceBaseRepository<Cargo, Integer> interfaceBaseRepository) {
        super(interfaceBaseRepository);
    }

    /**
     * servicio lista los cargos.
     * @return
     * @throws Exception
     */
    @Override
    public List<String> listCargos() throws Exception {
        try {
            List<Cargo> cargos = cargoRepository.findAll();
            return cargos.stream()
                    .sorted(Comparator.comparing(e -> e.getDescripcionCargo()))
                    .map(e -> e.getDescripcionCargo())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
