package com.example.estacionamento.shared.dto.response;

import java.time.LocalDateTime;

public record MovimentacaoVeiculoResponseDTO(
        Long id,
        VeiculoResponseDTO veiculo,
        EstabelecimentoResponseDTO estabelecimento,
        LocalDateTime horarioEntrada,
        LocalDateTime horarioSaida,
        boolean ativo
) {}
