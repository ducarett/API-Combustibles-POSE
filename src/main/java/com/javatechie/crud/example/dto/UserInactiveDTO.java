package com.javatechie.crud.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "apellido",
        "nombre",
        "usuario del sistema",
        "legajo",
        "cargo",
        "fecha de baja",
        "hora de baja",
        "usuario que dio la baja"
})
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInactiveDTO {

    @JsonProperty("apellido")
    private String apellido;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("usuario del sistema")
    private String login;

    @JsonProperty("legajo")
    private Integer legajo;

    @JsonProperty("cargo")
    private String descripcionCargo;

    @JsonProperty("fecha de baja")
    private LocalDate fechaBaja;

    @JsonProperty("hora de baja")
    private LocalDateTime horaBaja;

    @JsonProperty("usuario que dio la baja")
    private Integer usuarioBaja;

}

