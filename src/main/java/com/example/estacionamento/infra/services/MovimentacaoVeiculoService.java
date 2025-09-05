package com.example.estacionamento.infra.services;


import com.example.estacionamento.domain.enums.TipoVeiculo;
import com.example.estacionamento.domain.model.Estabelecimento;
import com.example.estacionamento.domain.model.MovimentacaoVeiculo;
import com.example.estacionamento.domain.model.Veiculo;
import com.example.estacionamento.exceptions.EstabelecimentoLotadoException;
import com.example.estacionamento.exceptions.VeiculoNaoEstacionadoException;
import com.example.estacionamento.exceptions.MovimentacaoNaoEncontradaException;
import com.example.estacionamento.infra.repositories.EstabelecimentoRepository;
import com.example.estacionamento.infra.repositories.MovimentacaoRepository;
import com.example.estacionamento.shared.dto.request.MovimentacaoVeiculoRequesDTO;
import com.example.estacionamento.shared.dto.request.MovimentacaoVeiculoSaidaRequestDTO;
import com.example.estacionamento.shared.dto.response.MovimentacaoVeiculoResponseDTO;
import com.example.estacionamento.shared.mapper.MovimentacaoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoVeiculoService {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private MovimentacaoMapper movimentacaoMapper;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private VeiculoService veiculoService;

    @Transactional
    public MovimentacaoVeiculoResponseDTO registrarEntrada(MovimentacaoVeiculoRequesDTO requesDTO) {
        Veiculo veiculo=this.veiculoService.getVehicle(requesDTO.veiculoId());
        Estabelecimento estabelecimento=this.estabelecimentoService.getEstabelecimento(requesDTO.estabelecimentoId());
        if(estabelecimento.getQtdDeVagasParaCarro()==0 && veiculo.getTipo().equals(TipoVeiculo.CARRO)){
            throw  new EstabelecimentoLotadoException("Não há mais vagas para carros");
        }

        if(estabelecimento.getQtdDeVagasParaMoto()==0 && veiculo.getTipo().equals(TipoVeiculo.MOTO)){
            throw  new EstabelecimentoLotadoException("Não há mais vagas para motos");
        }
        MovimentacaoVeiculo movimentacaoVeiculo=new MovimentacaoVeiculo();
        movimentacaoVeiculo.setVeiculo(veiculo);
        movimentacaoVeiculo.setEstabelecimento(estabelecimento);
        movimentacaoVeiculo.setAtivo(true);
        movimentacaoVeiculo.setHorarioEntrada(LocalDateTime.now());
        if(veiculo.getTipo().equals(TipoVeiculo.CARRO)){
            estabelecimento.setQtdDeVagasParaCarro(estabelecimento.getQtdDeVagasParaCarro() -1);
        }

        if(veiculo.getTipo().equals(TipoVeiculo.MOTO)){
            estabelecimento.setQtdDeVagasParaMoto(estabelecimento.getQtdDeVagasParaMoto() -1 );
        }
        this.estabelecimentoRepository.save(estabelecimento);
        MovimentacaoVeiculo save=this.movimentacaoRepository.save(movimentacaoVeiculo);
        return this.movimentacaoMapper.toResponseDTO(save);
    }


    @Transactional
    public MovimentacaoVeiculoResponseDTO registrarSaida(MovimentacaoVeiculoSaidaRequestDTO requesDTO) {
        MovimentacaoVeiculo movimentacaoVeiculo=this.getMovimentacao(requesDTO.id());
        if(!movimentacaoVeiculo.isAtivo()){
            throw  new VeiculoNaoEstacionadoException("Esse veiculo não está estacionado");
        }
        movimentacaoVeiculo.setHorarioSaida(LocalDateTime.now());
        movimentacaoVeiculo.setAtivo(false);
        TipoVeiculo tipo=movimentacaoVeiculo.getVeiculo().getTipo();
        Estabelecimento estabelecimento=movimentacaoVeiculo.getEstabelecimento();
        if (tipo.equals(TipoVeiculo.CARRO)) {
            estabelecimento.setQtdDeVagasParaCarro(estabelecimento.getQtdDeVagasParaCarro() + 1);
        } else if (tipo.equals(TipoVeiculo.MOTO)) {
            estabelecimento.setQtdDeVagasParaMoto(estabelecimento.getQtdDeVagasParaMoto() + 1);
        }

        estabelecimentoRepository.save(estabelecimento);
        MovimentacaoVeiculo save=this.movimentacaoRepository.save(movimentacaoVeiculo);
        return this.movimentacaoMapper.toResponseDTO(save);
    }

    public List<MovimentacaoVeiculoResponseDTO> getMovimentacoes(){
        return this.movimentacaoMapper.toListResponseDTO(movimentacaoRepository.findAll());
    }


    public MovimentacaoVeiculo getMovimentacao(Long id){
        return this.movimentacaoRepository.findById(id)
                .orElseThrow(()->new MovimentacaoNaoEncontradaException());
    }



}
