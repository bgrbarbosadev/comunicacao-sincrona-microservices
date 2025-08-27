package br.com.bgrbarbosa.ms_agendamento.controller;

import br.com.bgrbarbosa.ms_agendamento.controller.dto.AgendamentoDTO;
import br.com.bgrbarbosa.ms_agendamento.controller.mapper.AgendamentoMapper;
import br.com.bgrbarbosa.ms_agendamento.model.Agendamento;
import br.com.bgrbarbosa.ms_agendamento.service.AgendamentoService;
import br.com.bgrbarbosa.ms_agendamento.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/agendamento")
public class AgendamentoController {

    @Autowired
    AgendamentoService service;

    @Autowired
    AgendamentoMapper mapper;

    @GetMapping
    public ResponseEntity<Object>findAll() {
        List<Agendamento> list = service.findAll();
        return ResponseEntity.ok().body(mapper.toDtoList(list));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object>findById(@PathVariable(value = "id") UUID id) {
      Optional<Agendamento> obj = service.findById(id);
      if (!obj.isPresent()) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Registro não encontrado - ID: " + id));
      }
      return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj.get()));
    }

    @PostMapping
    public ResponseEntity<Object> saveAgendamentoConvidado(@RequestBody AgendamentoDTO agendamento) {
        Agendamento obj = service.save(mapper.toEntity(agendamento));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj));
    }

    @PutMapping
    public ResponseEntity<Object> updateAgendamentoConvidado(@RequestBody AgendamentoDTO agendamento) {
        Agendamento obj = service.save(mapper.toEntity(agendamento));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAgendamentoConvidado(@PathVariable(value = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Agendamento excluído com sucesso!");
    }


}
