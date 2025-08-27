package br.com.bgrbarbosa.eventos_api.service.impl;

import br.com.bgrbarbosa.eventos_api.model.Evento;
import br.com.bgrbarbosa.eventos_api.repository.EventoRepository;
import br.com.bgrbarbosa.eventos_api.service.EventoService;
import br.com.bgrbarbosa.eventos_api.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@ResponseBody
public class EventoServiceImpl implements EventoService {

    private final EventoRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();

    private final String URL_EVENTO_AGENDA = "http://localhost:8084";

    @Override
    public List<Evento> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Evento> findById(UUID id) throws ResourceNotFoundException {
        Optional<Evento> aux =  repository.findById(id);
        if (!aux.isPresent()) {
            throw new ResourceNotFoundException("Registro não encontrado: " + id);
        }
        return aux;
    }

    @Override
    public Evento insert(Evento evento) throws Exception {
        Evento aux = new Evento();
        try {
            aux = repository.save(evento);
            ResponseEntity<Evento> response = restTemplate.postForEntity(
                    URL_EVENTO_AGENDA + "/agendamento/evento",
                    aux,
                    Evento.class);
            return aux;
        } catch (Exception e) {
            repository.deleteById(aux.getIdEvento());
            throw new Exception("Não foi possível inserir o evento" + aux.getIdEvento());
        }
    }

    @Override
    public Evento updateEvento(Evento evento) throws ResourceNotFoundException, Exception {
        Optional<Evento> aux =  repository.findById(evento.getIdEvento());
        if (!aux.isPresent()) {
            throw new ResourceNotFoundException("Registro não encontrado: " + evento.getIdEvento());
        }

        Evento eventoAux =  repository.getOne(evento.getIdEvento());
        eventoAux.setTitleEvento(evento.getTitleEvento());
        eventoAux.setDescEvento(evento.getDescEvento());
        eventoAux.setDtEvento(evento.getDtEvento());
        eventoAux.setHrEvento(evento.getHrEvento());
        try {
            ResponseEntity<Evento> response = restTemplate.postForEntity(
                    URL_EVENTO_AGENDA + "/agendamento/evento",
                    eventoAux,
                    Evento.class);
            repository.save(eventoAux);
            return eventoAux;
        } catch (Exception e) {
            throw new Exception("Não foi possível inserir o evento" + evento.getIdEvento());
        }
    }

    @Override
    public void delete(UUID uuid) throws ResourceNotFoundException, Exception {
        if (!repository.existsById(uuid)) {
            throw new ResourceNotFoundException("Registro não encontrado" + uuid);
        }
        try {
            String urlDelecao = URL_EVENTO_AGENDA + "/agendamento/evento/" + uuid;
            restTemplate.delete(urlDelecao,uuid);
            repository.deleteById(uuid);
        } catch (Exception e) {
            throw new Exception("Não foi possível inserir o evento" + uuid);
        }

    }
}
