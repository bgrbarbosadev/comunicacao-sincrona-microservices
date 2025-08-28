package br.com.bgrbarbosa.ms_agendamento.service;



import br.com.bgrbarbosa.ms_agendamento.model.Agendamento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgendamentoService {

    List<Agendamento> findAll();

    Optional<Agendamento> findById(UUID id);

    Agendamento save(Agendamento agendamento);

    Agendamento update(Agendamento agendamento);

    void confirm(UUID id);

    void cancel(UUID id);

    void delete(UUID uuid);

    void enviar(Agendamento agendamento, String assunto, String mensagem);
}
