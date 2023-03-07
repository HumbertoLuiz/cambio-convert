package com.cambio.convert.dtos;

import com.cambio.convert.models.Moeda;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class CambioResponse {

    @NotNull
    @Positive
    private Double valorAConverter;

    @NotNull
    private String tipoConversao;

    @NotNull
    private Moeda moedas;
}