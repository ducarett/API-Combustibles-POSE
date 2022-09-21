package com.javatechie.crud.example.service.Impl.implement;

import com.javatechie.crud.example.entity.Cargo;
import com.javatechie.crud.example.repository.InterfaceBaseRepository;
import com.javatechie.crud.example.service.Impl.abstractClass.BaseServiceImpl;
import com.javatechie.crud.example.service.interfaz.CargoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoServiceImpl extends BaseServiceImpl<Cargo, Integer> implements CargoService {

    public CargoServiceImpl(InterfaceBaseRepository<Cargo, Integer> interfaceBaseRepository) {
        super(interfaceBaseRepository);
    }

    /**
     * servicio lista los cargos.
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Cargo> listCargos() throws Exception {
        try {
            return interfaceBaseRepository.findAll();
            //return cargos.stream().sorted(Comparator.comparing(e -> e.getDescripcionCargo()));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
