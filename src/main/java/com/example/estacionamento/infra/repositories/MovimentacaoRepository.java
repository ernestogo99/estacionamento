package com.example.estacionamento.infra.repositories;

import com.example.estacionamento.domain.model.MovimentacaoVeiculo;
import com.example.estacionamento.shared.dto.response.RelatorioSumarioResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<MovimentacaoVeiculo,Long> {

    @Query("""
    SELECT 
        SUM(CASE WHEN m.ativo = true THEN 1 ELSE 0 END),
        SUM(CASE WHEN m.horarioSaida IS NOT NULL THEN 1 ELSE 0 END),
        SUM(CASE WHEN m.horarioEntrada IS NOT NULL THEN 1 ELSE 0 END)
    FROM MovimentacaoVeiculo m
""")
    List<Object[]> getSumarioEntradaESaida();


    @Query(value = """
    SELECT 
        TO_CHAR(horario_entrada, 'HH24:MI') AS hora_minuto,
        COUNT(*) AS total_entradas
    FROM movimentacao_veiculo
    WHERE horario_entrada IS NOT NULL
    GROUP BY hora_minuto
    ORDER BY hora_minuto
""", nativeQuery = true)
    List<Object[]> getEntradasPorHora();



    @Query(value = """
    SELECT 
        TO_CHAR(horario_saida, 'HH24:MI') AS hora_minuto,
        COUNT(*) AS total_saidas
    FROM movimentacao_veiculo
    WHERE horario_saida IS NOT NULL
    GROUP BY hora_minuto
    ORDER BY hora_minuto
""", nativeQuery = true)
    List<Object[]> getSaidasPorHora();


}
