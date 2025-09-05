package com.example.estacionamento.infra.repositories;

import com.example.estacionamento.domain.model.MovimentacaoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<MovimentacaoVeiculo,Long> {
}
