package com.example.estacionamento.infra.repositories;

import com.example.estacionamento.domain.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento,Long> {

}
