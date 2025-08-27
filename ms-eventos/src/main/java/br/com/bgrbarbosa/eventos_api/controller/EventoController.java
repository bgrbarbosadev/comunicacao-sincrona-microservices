package br.com.bgrbarbosa.eventos_api.controller;

import br.com.bgrbarbosa.eventos_api.controller.dto.EventoDTO;
import br.com.bgrbarbosa.eventos_api.controller.mapper.EventoMapper;
import br.com.bgrbarbosa.eventos_api.model.Evento;
import br.com.bgrbarbosa.eventos_api.service.EventoService;
import br.com.bgrbarbosa.eventos_api.service.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/evento")
@AllArgsConstructor
public class EventoController {

    private final EventoService service;

    private final EventoMapper mapper;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        List<EventoDTO> list = mapper.toDtoList(service.findAll());
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object>findById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        Optional<Evento> obj = service.findById(id);
        if (!obj.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Registro não encontrado - ID: " + id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj.get()));
    }

    @PostMapping
    public ResponseEntity<Object>insert(@RequestBody @Valid EventoDTO dto) throws Exception {
        Evento evento = service.insert(mapper.toEntity(dto));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(evento.getIdEvento()).toUri();
        return ResponseEntity.created(uri).body(mapper.toDto(evento));
    }

    @PutMapping
    public ResponseEntity<EventoDTO> update(@RequestBody @Valid EventoDTO dto) throws ResourceNotFoundException, Exception {
        if (service.findById(dto.getIdEvento()).isPresent()) {
            Evento evento = service.updateEvento(mapper.toEntity(dto));
            return ResponseEntity.ok().body(mapper.toDto(evento));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object>  delete(@PathVariable UUID id) throws ResourceNotFoundException, Exception {
        Optional<Evento> aux = service.findById(id);
        if (aux.isPresent()) {
            service.delete(id);
            return ResponseEntity.ok().body("Registro deletado com sucesso!!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
