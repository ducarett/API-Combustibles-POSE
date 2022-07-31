package com.javatechie.crud.example.service.interfaz;

import com.javatechie.crud.example.entity.Cargo;

import java.util.List;

public interface CargoService extends BaseService<Cargo, Integer> {

    List<Cargo> listCargos() throws Exception;
}
