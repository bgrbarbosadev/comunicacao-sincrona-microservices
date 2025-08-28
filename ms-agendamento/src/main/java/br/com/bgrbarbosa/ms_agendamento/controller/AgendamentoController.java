package br.com.bgrbarbosa.ms_agendamento.controller;

import br.com.bgrbarbosa.ms_agendamento.controller.dto.AgendamentoDTO;
import br.com.bgrbarbosa.ms_agendamento.controller.mapper.AgendamentoMapper;
import br.com.bgrbarbosa.ms_agendamento.model.Agendamento;
import br.com.bgrbarbosa.ms_agendamento.service.AgendamentoService;
import br.com.bgrbarbosa.ms_agendamento.service.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Agendamento", description = "Contem as operações de Cadastro, Atualização, Buscas e Deleção de registros de agendamento")
public class AgendamentoController {

    @Autowired
    AgendamentoService service;

    @Autowired
    AgendamentoMapper mapper;

    @Operation(summary = "Listar todos os Agendamento", description = "Listar todos os agendamentos cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista todos os agendamentos cadastrados",
                            content = @Content(mediaType = "application/json"))
            })
    @GetMapping
    public ResponseEntity<Object>findAll() {
        List<Agendamento> list = service.findAll();
        return ResponseEntity.ok().body(mapper.toDtoList(list));
    }

    @Operation(summary = "Recuperar um agendamento pelo id", description = "Recuperar um agendamento pelo id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Agendamento recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendamentoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class)))
            })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object>findById(@PathVariable(value = "id") UUID id) {
      Optional<Agendamento> obj = service.findById(id);
      if (!obj.isPresent()) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Registro não encontrado - ID: " + id));
      }
      return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj.get()));
    }

    @Operation(summary = "Cria uma novo agendamento", description = "Recurso para criar um novo agendamento",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendamentoDTO.class)))
            })
    @PostMapping
    public ResponseEntity<Object> saveAgendamentoConvidado(@RequestBody AgendamentoDTO agendamento) {
        Agendamento obj = service.save(mapper.toEntity(agendamento));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj));
    }

    @Operation(summary = "Atualizar agendamento", description = "Atualizar agedamento",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Agendamento atualizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class)))
            })
    @PutMapping
    public ResponseEntity<Object> updateAgendamentoConvidado(@RequestBody AgendamentoDTO agendamento) {
        Agendamento obj = service.update(mapper.toEntity(agendamento));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(obj));
    }

    @Operation(summary = "Confirmar presença", description = "Confirmar presença",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Presença confirmada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class)))
            })
    @PutMapping(value = "/confirmacao/{id}")
    public ResponseEntity<Object> confirmarAgendamentoConvidado(@PathVariable(value = "id") UUID id) {
        service.confirm(id);
        return ResponseEntity.status(HttpStatus.OK).body("Presença confirmada");
    }

    @Operation(summary = "Cancelar presença", description = "Cancelar presença",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Presença cancelada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class)))
            })
    @PutMapping(value = "/cancelamento/{id}")
    public ResponseEntity<Object> cancelarAgendamentoConvidado(@PathVariable(value = "id") UUID id) {
        service.cancel(id);
        return ResponseEntity.status(HttpStatus.OK).body("Presença cancelada");
    }

    @Operation(summary = "Deletar um agendamento pelo id", description = "Deletar um agendamento pelo ID",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Agendamento deletado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendamentoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class)))
            })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAgendamentoConvidado(@PathVariable(value = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Agendamento excluído com sucesso!");
    }


}
