package br.com.bgrbarbosa.ms_convidados.controller;


import br.com.bgrbarbosa.ms_convidados.controller.dto.ConvidadoDTO;
import br.com.bgrbarbosa.ms_convidados.controller.mapper.ConvidadoMapper;
import br.com.bgrbarbosa.ms_convidados.model.Convidado;
import br.com.bgrbarbosa.ms_convidados.service.ConvidadoService;
import br.com.bgrbarbosa.ms_convidados.service.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Convidados", description = "Contem as operações de Cadastro, Atualização, Buscas e Deleção de registros de convidados")
public class ConvidadoController {

    @Autowired
    ConvidadoService service;

    @Autowired
    ConvidadoMapper mapper;

    @Operation(summary = "Listar todos os convidados", description = "Listar todos os cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista todos eventos cadastrados",
                            content = @Content(mediaType = "application/json"))
            })
    @GetMapping
    public ResponseEntity<Object>findAll() {
        List<Convidado> list = service.findAll();
        return ResponseEntity.ok().body(mapper.toDtoList(list));
    }

    @Operation(summary = "Recuperar um convidado pelo id", description = "Recuperar um convidado pelo id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Convidado recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConvidadoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class)))
            })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object>findById(@PathVariable(value = "id") UUID id) {
      Optional<Convidado>obj = service.findById(id);
      if (!obj.isPresent()) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Resgistro não encontrado - ID: " + id));
      }
      return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj.get()));
    }

    @Operation(summary = "Recuperar um convidado pelo cpf", description = "Recuperar um convidado pelo cpf",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Convidado recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConvidadoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class)))
            })
    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<Object>findByCpf(@PathVariable(value = "cpf")  String cpf) {
      Optional<Convidado>obj = service.findByCpf(cpf);
      if (!obj.isPresent()) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Registro não encontrado- CPF: " + cpf));
      }
      return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj.get()));
    }

    @Operation(summary = "Cria uma novo convidado", description = "Recurso para criar um novo convidado",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Convidado criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConvidadoDTO.class)))
            })
    @PostMapping
    public ResponseEntity<Object>insert(@RequestBody @Valid ConvidadoDTO dto) throws Exception {
        Convidado result = service.insert(mapper.toEntity(dto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getIdConvidado()).toUri();
        return ResponseEntity.created(uri).body(mapper.toDto(result));
    }

    @Operation(summary = "Atualizar convidado", description = "Atualizar convidado",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Convidado atualizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class)))
            })
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody @Valid ConvidadoDTO dto) throws Exception {
        if (service.findById(dto.getIdConvidado()).isPresent()) {
            Convidado convidado = service.updateConvidado(mapper.toEntity(dto));
            return ResponseEntity.ok().body(mapper.toDto(convidado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deletar um convidado pelo id", description = "Deletar um convidado pelo ID",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Convidado deletado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConvidadoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class)))
            })
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
