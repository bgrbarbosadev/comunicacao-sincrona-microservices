package br.com.bgrbarbosa.eventos_api.service;

import br.com.bgrbarbosa.eventos_api.model.Evento;
import br.com.bgrbarbosa.eventos_api.service.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventoService {

    List<Evento> findAll();

    Optional<Evento> findById(UUID id) throws ResourceNotFoundException;

    Evento insert(Evento evento) throws Exception;

    Evento updateEvento(Evento evento) throws ResourceNotFoundException, Exception;

    void delete(UUID id) throws ResourceNotFoundException, Exception;

}
