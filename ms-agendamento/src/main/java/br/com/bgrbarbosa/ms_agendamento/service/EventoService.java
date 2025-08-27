package br.com.bgrbarbosa.ms_agendamento.service;



import br.com.bgrbarbosa.ms_agendamento.model.Evento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface EventoService {

    List<Evento>findAll();

    Optional<Evento> findById(UUID uuid);

    void insert(Evento evento);

    void delete(UUID uuid);
}
