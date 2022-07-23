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
        "apellido",
        "nombre",
        "numero de legajo"
})
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaquinistaActivoDTO {

    @JsonProperty("apellido")
    private String apellido;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("numero de legajo")
    private Integer legajo;

}

