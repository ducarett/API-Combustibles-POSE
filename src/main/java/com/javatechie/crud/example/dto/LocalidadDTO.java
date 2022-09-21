package com.javatechie.crud.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "provincia",
        "localidad",
        "codigoPostal",
        "activo"
})
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalidadDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("provincia")
    private String provincia;

    @JsonProperty("localidad")
    private String localidad;

    @JsonProperty("codigoPostal")
    private Integer codigoPostal;

    @JsonProperty("activo")
    private String activo;

}
