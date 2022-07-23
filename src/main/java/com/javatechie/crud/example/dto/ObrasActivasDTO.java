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
        "Nro obra",
        "descripcion",
        "provincia",
        "localidad",
        "gerente",
        "jefe",
        "administrativo"
})
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObrasActivasDTO {

    @JsonProperty("Nro obra")
    private Integer obraId;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("provincia")
    private String Provincia;

    @JsonProperty("localidad")
    private String Localidad;

    @JsonProperty("gerente")
    private String gerente;

    @JsonProperty("jefe")
    private String jefe;

    @JsonProperty("administrativo")
    private String administrativo;
}
