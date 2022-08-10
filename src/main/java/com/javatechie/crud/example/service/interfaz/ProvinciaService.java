package com.javatechie.crud.example.service.interfaz;


import com.javatechie.crud.example.entity.Provincia;

import java.util.List;

public interface ProvinciaService extends BaseService<Provincia,Integer >{

    List<Provincia> listaProvincias() throws Exception;
}
