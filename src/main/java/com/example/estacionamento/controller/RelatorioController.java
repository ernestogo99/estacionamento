package com.example.estacionamento.controller;

import com.example.estacionamento.infra.services.RelatorioService;
import com.example.estacionamento.shared.dto.response.RelatorioHoraResponseDTO;
import com.example.estacionamento.shared.dto.response.RelatorioSumarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
@Tag(name = "Relatórios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/sumario")
    @Operation(summary = "Retorna o sumário de entrada e saída")
    public ResponseEntity<RelatorioSumarioResponseDTO> getSumario() {
        return ResponseEntity.ok(relatorioService.getSumarioEntradaESaida());
    }


    @GetMapping("/entradas-por-hora")
    @Operation(summary = "Retorna a quantidade de entradas por hora")
    public ResponseEntity<List<RelatorioHoraResponseDTO>> getEntradasPorHora() {
        return ResponseEntity.ok(relatorioService.getEntradasPorHora());
    }

    @GetMapping("/saidas-por-hora")
    @Operation(summary = "Retorna a quantidade de saídas por hora")
    public ResponseEntity<List<RelatorioHoraResponseDTO>> getSaidasPorHora() {
        return ResponseEntity.ok(relatorioService.getSaidasPorHora());
    }
}