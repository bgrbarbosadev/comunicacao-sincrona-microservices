package br.com.bgrbarbosa.ms_agendamento.service;


import br.com.bgrbarbosa.ms_agendamento.model.Convidado;
import br.com.bgrbarbosa.ms_agendamento.model.Evento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ConvidadoService{

    List<Convidado>findAll();

    Optional<Convidado> findById(UUID id);

    Optional<Convidado> findByCpf(String cpf);

    void insert(Convidado convidado);

    void delete(UUID uuid);

}
