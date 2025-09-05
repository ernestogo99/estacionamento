package com.example.estacionamento.shared.dto.request;

import jakarta.validation.constraints.NotNull;

public record MovimentacaoVeiculoRequesDTO(
        @NotNull Long veiculoId,
        @NotNull Long estabelecimentoId
) {
}
