package com.example.estacionamento.shared.mapper;


import com.example.estacionamento.domain.model.Veiculo;
import com.example.estacionamento.shared.dto.request.VeiculoRequestDTO;
import com.example.estacionamento.shared.dto.response.VeiculoResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {

    Veiculo toEntity(VeiculoRequestDTO veiculoRequestDTO);

    VeiculoRequestDTO toRequestDTO(Veiculo veiculo);

    VeiculoResponseDTO toResponseDTO(Veiculo veiculo);

    List<VeiculoResponseDTO> toListResponseDTO(List<Veiculo> veiculos);
}
