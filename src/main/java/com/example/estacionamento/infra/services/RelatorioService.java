package com.example.estacionamento.infra.services;

import com.example.estacionamento.infra.repositories.MovimentacaoRepository;
import com.example.estacionamento.shared.dto.response.RelatorioHoraResponseDTO;
import com.example.estacionamento.shared.dto.response.RelatorioSumarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;


    public RelatorioSumarioResponseDTO getSumarioEntradaESaida() {
        Object[] result = movimentacaoRepository.getSumarioEntradaESaida().get(0);
        Long entradas       = ((Number) result[2]).longValue();
        Long saidas         = ((Number) result[1]).longValue();
        Long veiculosAtivos = ((Number) result[0]).longValue();

        return new RelatorioSumarioResponseDTO(entradas, saidas,veiculosAtivos);
    }


    public List<RelatorioHoraResponseDTO> getEntradasPorHora() {
        List<Object[]> result = movimentacaoRepository.getEntradasPorHora();
        return result.stream()
                .map(r -> new RelatorioHoraResponseDTO(
                        ((String) r[0]),
                        ((Number) r[1]).longValue()
                ))
                .toList();
    }

    public List<RelatorioHoraResponseDTO> getSaidasPorHora() {
        List<Object[]> result = movimentacaoRepository.getSaidasPorHora();
        return result.stream()
                .map(r -> new RelatorioHoraResponseDTO(
                        ((String) r[0]),
                        ((Number) r[1]).longValue()
                ))
                .toList();
    }
}
