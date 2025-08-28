package br.com.bgrbarbosa.ms_agendamento.controller;



import br.com.bgrbarbosa.ms_agendamento.controller.mapper.ConvidadoMapper;
import br.com.bgrbarbosa.ms_agendamento.model.Convidado;
import br.com.bgrbarbosa.ms_agendamento.service.ConvidadoService;
import br.com.bgrbarbosa.ms_agendamento.service.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/agendamento/convidado")
@Hidden
public class ConvidadoController {

    @Autowired
    ConvidadoService service;

    @Autowired
    ConvidadoMapper mapper;

    @GetMapping
    public ResponseEntity<Object>findAll() {
        List<Convidado> list = service.findAll();
        return ResponseEntity.ok().body(mapper.toDtoList(list));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object>findById(@PathVariable(value = "id") UUID id) {
      Optional<Convidado>obj = service.findById(id);
      if (!obj.isPresent()) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Registro não encontrado - ID: " + id));
      }
      return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj.get()));
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<Object>findByCpf(@PathVariable(value = "cpf")  String cpf) {
      Optional<Convidado>obj = service.findByCpf(cpf);
      if (!obj.isPresent()) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Registro não encontrado - CPF: " + cpf));
      }
      return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj.get()));
    }

    @PostMapping
    public void saveAgendamentoConvidado(@RequestBody Convidado convidado) {
        service.insert(convidado);
    }

    @PutMapping
    public void updateAgendamentoConvidado(@RequestBody Convidado convidado) {
        service.insert(convidado);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAgendamentoConvidado(@PathVariable(value = "id") UUID id) {
        service.delete(id);
    }


}
