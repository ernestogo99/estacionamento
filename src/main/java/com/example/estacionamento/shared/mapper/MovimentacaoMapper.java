package com.example.estacionamento.shared.mapper;


import com.example.estacionamento.domain.model.MovimentacaoVeiculo;
import com.example.estacionamento.shared.dto.request.MovimentacaoVeiculoRequesDTO;
import com.example.estacionamento.shared.dto.response.MovimentacaoVeiculoResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {EstabelecimentoMapper.class, VeiculoMapper.class})
public interface MovimentacaoMapper {

    MovimentacaoVeiculo toEntiTy(MovimentacaoVeiculoRequesDTO movimentacaoVeiculoRequesDTO);

    MovimentacaoVeiculoRequesDTO toRequestDTO(MovimentacaoVeiculo movimentacaoVeiculo);

    MovimentacaoVeiculoResponseDTO toResponseDTO(MovimentacaoVeiculo movimentacaoVeiculo);

    List<MovimentacaoVeiculoResponseDTO> toListResponseDTO(List<MovimentacaoVeiculo> movimentacaoVeiculos);
}
