package com.example.estacionamento.shared.dto.response;

public record RelatorioSumarioResponseDTO(
        long totalEntradas,
        long totalSaidas,
        long veiculosAtivos
) {}
