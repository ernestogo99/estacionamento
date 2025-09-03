package com.example.estacionamento.infra.services;


import com.example.estacionamento.domain.model.Estabelecimento;
import com.example.estacionamento.exceptions.EstabelecimentoNaoEncontradoException;
import com.example.estacionamento.infra.repositories.EstabelecimentoRepository;
import com.example.estacionamento.shared.dto.request.EstabelecimentoRequestDTO;
import com.example.estacionamento.shared.dto.response.EstabelecimentoResponseDTO;
import com.example.estacionamento.shared.mapper.EstabelecimentoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private EstabelecimentoMapper estabelecimentoMapper;


    public EstabelecimentoResponseDTO createEstabelecimento(EstabelecimentoRequestDTO estabelecimentoRequestDTO){
        Estabelecimento estabelecimento=this.estabelecimentoMapper.toEntity(estabelecimentoRequestDTO);
        Estabelecimento save=this.estabelecimentoRepository.save(estabelecimento);
        return this.estabelecimentoMapper.toResponseDTO(save);
    }

    private Estabelecimento getEstabelecimento(Long id){
        return this.estabelecimentoRepository.findById(id)
                .orElseThrow(()->new EstabelecimentoNaoEncontradoException());
    }

    public List<EstabelecimentoResponseDTO> getAll(){
        return this.estabelecimentoMapper.toListResponseDTO(this.estabelecimentoRepository.findAll());
    }

    public void deleteEstabelecimento(Long id){
        Estabelecimento estabelecimento=this.getEstabelecimento(id);
        this.estabelecimentoRepository.delete(estabelecimento);
    }

    public EstabelecimentoResponseDTO getEstabelecimentoById(Long id){
        Estabelecimento estabelecimento=this.getEstabelecimento(id);
        return this.estabelecimentoMapper.toResponseDTO(estabelecimento);
    }

    public EstabelecimentoResponseDTO updateEstabelecimento(Long id,EstabelecimentoRequestDTO estabelecimentoRequestDTO){
        Estabelecimento estabelecimento=this.getEstabelecimento(id);    estabelecimento.setNome(estabelecimentoRequestDTO.nome());
        estabelecimento.setCnpj(estabelecimentoRequestDTO.cnpj());
        estabelecimento.setEndereco(estabelecimentoRequestDTO.endereco());
        estabelecimento.setTelefone(estabelecimentoRequestDTO.telefone());
        estabelecimento.setQtdDeVagasParaMoto(estabelecimentoRequestDTO.qtdDeVagasParaMoto());
        estabelecimento.setQtdDeVagasParaCarro(estabelecimentoRequestDTO.qtdDeVagasParaCarro());

        Estabelecimento atualizado = estabelecimentoRepository.save(estabelecimento);
        return this.estabelecimentoMapper.toResponseDTO(atualizado);

    }




}
