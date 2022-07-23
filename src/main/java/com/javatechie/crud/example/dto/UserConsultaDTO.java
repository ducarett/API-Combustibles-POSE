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
        "usuario del sistema",
        "legajo",
        "cargo",
        "activo"
})
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserConsultaDTO {

    @JsonProperty("apellido")
    private String apellido;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("usuario del sistema")
    private String login;

    @JsonProperty("legajo")
    private Integer legajo;

    @JsonProperty("cargo")
    private String cargo;

    @JsonProperty("activo")
    private String activo;
}
