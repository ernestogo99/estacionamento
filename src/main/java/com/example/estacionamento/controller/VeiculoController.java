package com.example.estacionamento.controller;

import com.example.estacionamento.infra.services.VeiculoService;
import com.example.estacionamento.shared.dto.request.VeiculoRequestDTO;
import com.example.estacionamento.shared.dto.response.VeiculoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@Tag(name = "Veículos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    @Operation(summary = "Cria um novo veículo", description = "Cria um veículo com todas as informações fornecidas")
    public ResponseEntity<VeiculoResponseDTO> createVehicle(
            @RequestBody @Valid VeiculoRequestDTO veiculoRequestDTO
    ){
        VeiculoResponseDTO response = this.veiculoService.createVehicle(veiculoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Lista todos os veículos", description = "Retorna uma lista de todos os veículos cadastrados")
    public ResponseEntity<List<VeiculoResponseDTO>> getAllVehicles(){
        return ResponseEntity.ok(this.veiculoService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca veículo por ID", description = "Retorna os detalhes de um veículo pelo seu ID")
    public ResponseEntity<VeiculoResponseDTO> getById(
            @Parameter(description = "ID do veículo") @PathVariable Long id
    ){
        return ResponseEntity.ok().body(this.veiculoService.getById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza veículo pelo ID", description = "Atualiza as informações de um veículo existente")
    public ResponseEntity<VeiculoResponseDTO> updateVehicle(
            @Parameter(description = "ID do veículo") @PathVariable Long id,
            @RequestBody @Valid VeiculoRequestDTO veiculoRequestDTO
    ){
        VeiculoResponseDTO updated = this.veiculoService.updateVehicle(id, veiculoRequestDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui veículo pelo ID", description = "Remove um veículo cadastrado pelo seu ID")
    public ResponseEntity<Void> deleteVehicle(
            @Parameter(description = "ID do veículo") @PathVariable Long id
    ){
        this.veiculoService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
