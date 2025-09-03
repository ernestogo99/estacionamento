package com.example.estacionamento.shared.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EstabelecimentoRequestDTO(
        @NotBlank String nome,
        @NotBlank String cnpj,
        @NotBlank String endereco,
        @NotBlank String telefone,
        @Min(0) int qtdDeVagasParaMoto,
        @Min(0) int qtdDeVagasParaCarro
) {
}
