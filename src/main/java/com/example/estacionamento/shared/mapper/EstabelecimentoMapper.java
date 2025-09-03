package com.example.estacionamento.shared.mapper;
import com.example.estacionamento.domain.model.Estabelecimento;
import com.example.estacionamento.shared.dto.request.EstabelecimentoRequestDTO;
import com.example.estacionamento.shared.dto.response.EstabelecimentoResponseDTO;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EstabelecimentoMapper {

    Estabelecimento toEntity(EstabelecimentoRequestDTO estabelecimentoRequestDTO);

    EstabelecimentoRequestDTO  toRequestDTO(Estabelecimento estabelecimento);

    EstabelecimentoResponseDTO toResponseDTO(Estabelecimento estabelecimento);

    List<EstabelecimentoResponseDTO> toListResponseDTO(List<Estabelecimento> estabelecimentos);
}
