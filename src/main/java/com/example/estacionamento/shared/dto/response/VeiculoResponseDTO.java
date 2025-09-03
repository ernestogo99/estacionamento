package com.example.estacionamento.shared.dto.response;

import com.example.estacionamento.domain.enums.TipoVeiculo;

public record VeiculoResponseDTO(
        Long id,
        String marca,
        String modelo,
        String cor,
        String placa,
        TipoVeiculo tipo
) {}