package br.com.bgrbarbosa.ms_agendamento.service.impl;

import br.com.bgrbarbosa.ms_agendamento.model.Evento;
import br.com.bgrbarbosa.ms_agendamento.repository.EventoRepository;
import br.com.bgrbarbosa.ms_agendamento.service.EventoService;
import br.com.bgrbarbosa.ms_agendamento.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    EventoRepository repository;

    @Override
    public List<Evento> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Evento> findById(UUID uuid) {
        Optional<Evento> aux =  repository.findById(uuid);
        if (!aux.isPresent()) {
            throw new ResourceNotFoundException("Entity not found: " + uuid);
        }
        return aux;
    }

    @Override
    public void insert(Evento evento){
        repository.save(evento);
    }

    @Override
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }


}
