package com.javatechie.crud.example.service.Impl;

import com.javatechie.crud.example.entity.Cargo;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.repository.CargoRepository;
import com.javatechie.crud.example.service.interfaz.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Cargo> listCargos() throws Exception {
        try {
            return cargoRepository.findAll();
            //return cargos.stream().sorted(Comparator.comparing(e -> e.getDescripcionCargo()));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
