package com.example.estacionamento.controller;

import com.example.estacionamento.infra.services.EstabelecimentoService;
import com.example.estacionamento.shared.dto.request.EstabelecimentoRequestDTO;
import com.example.estacionamento.shared.dto.response.EstabelecimentoResponseDTO;
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
@RequestMapping("/estabelecimentos")
@Tag(name = "Estabelecimentos")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @PostMapping
    @Operation(summary = "Cria um novo estabelecimento", description = "Cria um estabelecimento com todas as informações fornecidas")
    public ResponseEntity<EstabelecimentoResponseDTO> createEstabelecimento(
            @RequestBody @Valid EstabelecimentoRequestDTO estabelecimentoRequestDTO
    ){
        EstabelecimentoResponseDTO estabelecimentoResponseDTO = estabelecimentoService.createEstabelecimento(estabelecimentoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(estabelecimentoResponseDTO);
    }

    @GetMapping
    @Operation(summary = "Lista todos os estabelecimentos")
    public ResponseEntity<List<EstabelecimentoResponseDTO>> getAllEstabelecimentos(){
        return ResponseEntity.ok(estabelecimentoService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca estabelecimento por ID", description = "Retorna os detalhes de um estabelecimento pelo seu ID")
    public ResponseEntity<EstabelecimentoResponseDTO> getEstabelecimentoById(
            @Parameter(description = "ID do estabelecimento") @PathVariable Long id
    ){
        EstabelecimentoResponseDTO response = estabelecimentoService.getEstabelecimentoById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um estabelecimento pelo ID")
    public ResponseEntity<EstabelecimentoResponseDTO> updateEstabelecimento(
            @Parameter(description = "ID do estabelecimento") @PathVariable Long id,
            @RequestBody @Valid EstabelecimentoRequestDTO estabelecimentoRequestDTO
    ){
        EstabelecimentoResponseDTO response = estabelecimentoService.updateEstabelecimento(id, estabelecimentoRequestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um estabelecimento pelo ID")
    public ResponseEntity<Void> deleteEstabelecimento(
            @Parameter(description = "ID do estabelecimento") @PathVariable Long id
    ){
        this.estabelecimentoService.deleteEstabelecimento(id);
        return ResponseEntity.noContent().build();
    }
}
