package com.example.estacionamento.infra.services;


import com.example.estacionamento.domain.model.Veiculo;
import com.example.estacionamento.exceptions.VeiculoNaoEncontradoException;
import com.example.estacionamento.infra.repositories.VeiculoRepository;
import com.example.estacionamento.shared.dto.request.VeiculoRequestDTO;
import com.example.estacionamento.shared.dto.response.VeiculoResponseDTO;
import com.example.estacionamento.shared.mapper.VeiculoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private VeiculoMapper veiculoMapper;


    public VeiculoResponseDTO createVehicle(VeiculoRequestDTO veiculoRequestDTO){
        Veiculo veiculo=this.veiculoMapper.toEntity(veiculoRequestDTO);
        Veiculo save=this.veiculoRepository.save(veiculo);
        return this.veiculoMapper.toResponseDTO(save);
    }


    public List<VeiculoResponseDTO> getAll(){
        return this.veiculoMapper.toListResponseDTO(veiculoRepository.findAll());
    }

    public void deleteVehicle(Long id) {
        Veiculo veiculo = this.getVehicle(id);
        this.veiculoRepository.delete(veiculo);
    }

    public VeiculoResponseDTO getById(Long id){
        Veiculo veiculo=this.getVehicle(id);
        return this.veiculoMapper.toResponseDTO(veiculo);
    }


    @Transactional
    public VeiculoResponseDTO updateVehicle(Long id,VeiculoRequestDTO veiculoRequestDTO){
        Veiculo veiculo=this.getVehicle(id);
        veiculo.setPlaca(veiculoRequestDTO.placa());
        veiculo.setCor(veiculoRequestDTO.cor());
        veiculo.setTipo(veiculoRequestDTO.tipo());
        veiculo.setModelo(veiculoRequestDTO.modelo());
        veiculo.setMarca(veiculoRequestDTO.marca());
        Veiculo atualizado = this.veiculoRepository.save(veiculo);
        return this.veiculoMapper.toResponseDTO(atualizado);
    }

    public Veiculo getVehicle(Long id){
        return this.veiculoRepository.findById(id).orElseThrow(()->new VeiculoNaoEncontradoException());
    }

}
