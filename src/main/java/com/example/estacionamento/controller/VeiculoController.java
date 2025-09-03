package com.example.estacionamento.controller;


import com.example.estacionamento.infra.services.VeiculoService;
import com.example.estacionamento.shared.dto.request.VeiculoRequestDTO;
import com.example.estacionamento.shared.dto.response.VeiculoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> createVehicle(@RequestBody @Valid VeiculoRequestDTO veiculoRequestDTO){
        VeiculoResponseDTO response=this.veiculoService.createVehicle(veiculoRequestDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> getAllVehicles(){
        return ResponseEntity.ok(this.veiculoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.veiculoService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> updateVehicle(@PathVariable Long id,@RequestBody @Valid VeiculoRequestDTO veiculoRequestDTO){
        VeiculoResponseDTO updated=this.veiculoService.updateVehicle(id,veiculoRequestDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id){
        this.veiculoService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
