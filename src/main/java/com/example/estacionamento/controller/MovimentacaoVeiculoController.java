package com.example.estacionamento.controller;

import com.example.estacionamento.infra.services.MovimentacaoVeiculoService;
import com.example.estacionamento.shared.dto.request.MovimentacaoVeiculoRequesDTO;
import com.example.estacionamento.shared.dto.request.MovimentacaoVeiculoSaidaRequestDTO;
import com.example.estacionamento.shared.dto.response.MovimentacaoVeiculoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
@Tag(name = "Movimentações")
public class MovimentacaoVeiculoController {

    @Autowired
    private MovimentacaoVeiculoService movimentacaoVeiculoService;


    @GetMapping
    @Operation(summary = "Listar movimentações", description = "Retorna todas as movimentações registradas")
    public ResponseEntity<List<MovimentacaoVeiculoResponseDTO>> getAllMovimentacoes(){
        return ResponseEntity.ok(movimentacaoVeiculoService.getMovimentacoes());
    }


    @PostMapping("/entrada")
    @Operation(summary = "Registrar entrada de veículo", description = "Registra a entrada de um veículo no estacionamento")
    public ResponseEntity<MovimentacaoVeiculoResponseDTO> registrarEntrada(@RequestBody MovimentacaoVeiculoRequesDTO requesDTO){
        MovimentacaoVeiculoResponseDTO responseDTO=this.movimentacaoVeiculoService.registrarEntrada(requesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping("/saida")
    @Operation(summary = "Registrar saída de veículo", description = "Registra a saída de um veículo do estacionamento")
    public ResponseEntity<MovimentacaoVeiculoResponseDTO> registrarSaida(@RequestBody MovimentacaoVeiculoSaidaRequestDTO requestDTO){
        MovimentacaoVeiculoResponseDTO responseDTO=this.movimentacaoVeiculoService.registrarSaida(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
