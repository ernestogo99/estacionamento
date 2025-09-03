package com.example.estacionamento.shared.dto.response;

public record EstabelecimentoResponseDTO(
        Long id,
        String nome,
        String cnpj,
        String endereco,
        String telefone,
        int qtdDeVagasParaMoto,
        int qtdDeVagasParaCarro
) {
}
