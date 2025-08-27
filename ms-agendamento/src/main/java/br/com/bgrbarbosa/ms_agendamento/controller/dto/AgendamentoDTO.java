package br.com.bgrbarbosa.ms_agendamento.controller.dto;

import br.com.bgrbarbosa.ms_agendamento.model.Convidado;
import br.com.bgrbarbosa.ms_agendamento.model.Evento;
import br.com.bgrbarbosa.ms_agendamento.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgendamentoDTO {

    private UUID idAgendamento;

    private Status confirmacao;

    private LocalDate dataIncricao;

    private LocalDate dataAtualizacao;

    private Convidado convidado;

    private Evento evento;
}
