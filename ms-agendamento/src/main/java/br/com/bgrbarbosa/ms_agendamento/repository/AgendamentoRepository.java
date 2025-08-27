package br.com.bgrbarbosa.ms_agendamento.repository;

import br.com.bgrbarbosa.ms_agendamento.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {
}
