package com.example.estacionamento.infra.repositories;

import com.example.estacionamento.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {

}
