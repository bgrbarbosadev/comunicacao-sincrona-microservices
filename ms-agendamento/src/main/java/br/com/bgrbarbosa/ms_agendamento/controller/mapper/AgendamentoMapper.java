package br.com.bgrbarbosa.ms_agendamento.controller.mapper;

import br.com.bgrbarbosa.ms_agendamento.controller.dto.AgendamentoDTO;
import br.com.bgrbarbosa.ms_agendamento.controller.dto.ConvidadoDTO;
import br.com.bgrbarbosa.ms_agendamento.model.Agendamento;
import br.com.bgrbarbosa.ms_agendamento.model.Convidado;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AgendamentoMapper {

    AgendamentoDTO toDto(Agendamento agendamento);

    List<AgendamentoDTO> toDtoList(List<Agendamento> agendamento);

    Agendamento toEntity(AgendamentoDTO agendamentoDTO);

    List<Agendamento> toEntityList(List<AgendamentoDTO> agendamentoDTO);
}
