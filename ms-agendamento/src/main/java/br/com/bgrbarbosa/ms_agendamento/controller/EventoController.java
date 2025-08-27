package br.com.bgrbarbosa.ms_agendamento.controller;

import br.com.bgrbarbosa.ms_agendamento.controller.dto.EventoDTO;
import br.com.bgrbarbosa.ms_agendamento.controller.mapper.EventoMapper;
import br.com.bgrbarbosa.ms_agendamento.model.Evento;
import br.com.bgrbarbosa.ms_agendamento.service.EventoService;
import br.com.bgrbarbosa.ms_agendamento.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/agendamento/evento")
public class EventoController {

    @Autowired
    EventoService service;

    @Autowired
    EventoMapper mapper;


    @GetMapping
    public ResponseEntity<Object>findAll() {
        List<EventoDTO> list = mapper.toDtoList(service.findAll());
        return ResponseEntity.ok().body(list);
    }

  @GetMapping(value = "/{id}")
    public ResponseEntity<Object>findById(@PathVariable(value = "id") UUID id) {
      Optional<Evento> obj = service.findById(id);
      if (!obj.isPresent()) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Registro não encontrado - ID: " + id));
      }
      return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj.get()));
  }

  @PostMapping
  public void saveAgendamentoEvento(@RequestBody Evento evento) {
      service.insert(evento);
  }

  @PutMapping
  public void updateAgendamentoEvento(@RequestBody Evento evento) {
      service.insert(evento);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteAgendamentoEvento(@PathVariable(value = "id") UUID id) {
      service.delete(id);
  }

}
