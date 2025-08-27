package br.com.bgrbarbosa.ms_convidados.controller;


import br.com.bgrbarbosa.ms_convidados.controller.dto.ConvidadoDTO;
import br.com.bgrbarbosa.ms_convidados.controller.mapper.ConvidadoMapper;
import br.com.bgrbarbosa.ms_convidados.model.Convidado;
import br.com.bgrbarbosa.ms_convidados.service.ConvidadoService;
import br.com.bgrbarbosa.ms_convidados.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/convidado")
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
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Resgistro não encontrado - ID: " + id));
      }
      return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj.get()));
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<Object>findByCpf(@PathVariable(value = "cpf")  String cpf) {
      Optional<Convidado>obj = service.findByCpf(cpf);
      if (!obj.isPresent()) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Registro não encontrado- CPF: " + cpf));
      }
      return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj.get()));
    }

    @PostMapping
    public ResponseEntity<Object>insert(@RequestBody @Valid ConvidadoDTO dto) throws Exception {
        Convidado result = service.insert(mapper.toEntity(dto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getIdConvidado()).toUri();
        return ResponseEntity.created(uri).body(mapper.toDto(result));
    }
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody @Valid ConvidadoDTO dto) throws Exception {
        if (service.findById(dto.getIdConvidado()).isPresent()) {
            Convidado convidado = service.updateConvidado(mapper.toEntity(dto));
            return ResponseEntity.ok().body(mapper.toDto(convidado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> delete(@PathVariable UUID uuid) throws Exception {
        Optional<Convidado> aux = service.findById(uuid);
        if (aux.isPresent()) {
            service.delete(uuid);
            return ResponseEntity.ok().body("Registro deletado com sucesso!!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
