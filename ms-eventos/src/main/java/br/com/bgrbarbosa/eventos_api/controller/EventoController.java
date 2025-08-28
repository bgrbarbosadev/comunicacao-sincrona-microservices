package br.com.bgrbarbosa.eventos_api.controller;

import br.com.bgrbarbosa.eventos_api.controller.dto.EventoDTO;
import br.com.bgrbarbosa.eventos_api.controller.mapper.EventoMapper;
import br.com.bgrbarbosa.eventos_api.model.Evento;
import br.com.bgrbarbosa.eventos_api.service.EventoService;
import br.com.bgrbarbosa.eventos_api.service.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Eventos", description = "Contem as operações de Cadastro, Atualização, Buscas e Deleção de registros de eventos")
public class EventoController {

    private final EventoService service;

    private final EventoMapper mapper;

    @Operation(summary = "Listar todos os Eventos", description = "Listar todos as eventos cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista todos eventos cadastrados",
                            content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<Object> findAll() {
        List<EventoDTO> list = mapper.toDtoList(service.findAll());
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Recuperar uma evento pelo id", description = "Recuperar uma evento pelo id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evento recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class)))
            })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object>findById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        Optional<Evento> obj = service.findById(id);
        if (!obj.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Registro não encontrado - ID: " + id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj.get()));
    }

    @Operation(summary = "Cria uma novo evento", description = "Recurso para criar um novo evento",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Evento criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDTO.class)))
            })
    @PostMapping
    public ResponseEntity<Object>insert(@RequestBody @Valid EventoDTO dto) throws Exception {
        Evento evento = service.insert(mapper.toEntity(dto));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(evento.getIdEvento()).toUri();
        return ResponseEntity.created(uri).body(mapper.toDto(evento));
    }

    @Operation(summary = "Atualizar evento", description = "Atualizar evento",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Evento atualizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class)))
            })
    @PutMapping
    public ResponseEntity<EventoDTO> update(@RequestBody @Valid EventoDTO dto) throws ResourceNotFoundException, Exception {
        if (service.findById(dto.getIdEvento()).isPresent()) {
            Evento evento = service.updateEvento(mapper.toEntity(dto));
            return ResponseEntity.ok().body(mapper.toDto(evento));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deletar um evento pelo id", description = "Deletar um evento pelo ID",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Evento deletado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class)))
            })
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
