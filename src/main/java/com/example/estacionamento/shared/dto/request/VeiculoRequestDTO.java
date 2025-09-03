package com.example.estacionamento.shared.dto.request;

import com.example.estacionamento.domain.enums.TipoVeiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VeiculoRequestDTO(@NotBlank String marca,
                                @NotBlank String modelo,
                                @NotBlank String cor,
                                @NotBlank String placa,
                                @NotNull TipoVeiculo tipo) {
}
