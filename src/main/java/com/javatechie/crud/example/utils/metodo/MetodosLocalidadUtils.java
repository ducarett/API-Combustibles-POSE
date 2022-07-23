package com.javatechie.crud.example.utils.metodo;

import com.javatechie.crud.example.dto.LocalidadDTO;
import com.javatechie.crud.example.entity.Localidad;
import com.javatechie.crud.example.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetodosLocalidadUtils {
    /**
     * se hardcodeo el orden alfabetico de las provincias y se dividde en listas separadas a cada una, luego ordenamos alfabeticamente las localidades de cada provincia y se termina
     * unificando las listas.
     * @param entities
     * @return
     * @throws Exception
     */
    public List<LocalidadDTO> ordenarProvicnciasAlfabeticamente(List<Localidad> entities) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        String[] provincias = new String[]{"BUENOS AIRES", "CIUDAD AUTONOMA DE BUENOS AIRES", "CATAMARCA", "CHACO", "CHUBUT", "CORDOBA", "CORRIENTES", "ENTRE RIOS", "FORMOSA", "JUJUY", "LA PAMPA",
                "LA RIOJA", "MENDOZA", "MISIONES", "NEUQUEN", "RIO NEGRO", "SALTA", "SAN JUAN", "SAN LUIS", "SANTA CRUZ", "SANTA FE", "SANTIAGO DEL ESTERO", "TIERRA DEL FUEGO", "TUCUMAN"};

        List<List<Localidad>> listProvincias = new ArrayList<>();

        for (String aux : provincias) {

            List<Localidad> prov = new ArrayList<>();
            prov = entities.stream()
                    .filter(localidad -> localidad.getProvincia().getDescriptionProvincia().equals(aux))
                    .collect(Collectors.toList());

            if (!prov.isEmpty()) {
                listProvincias.add(localidadAlfabeticamente(prov));
            }
        }
        return listProvincias.stream().flatMap(List::stream).map(e -> modelMapper.map(e, LocalidadDTO.class)).collect(Collectors.toList());
    }

    /**
     * ordena alfabeticamente las localidades de una lista
     * @param entities
     * @return
     */
    public List<Localidad> localidadAlfabeticamente(List<Localidad> entities) {
        return entities.stream()
                .sorted(Comparator.comparing(e -> e.getDescripcionLocalidad()))
                .collect(Collectors.toList());
    }
}
