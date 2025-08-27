package br.com.bgrbarbosa.ms_agendamento.controller.mapper;

import br.com.bgrbarbosa.ms_agendamento.controller.dto.EventoDTO;
import br.com.bgrbarbosa.ms_agendamento.model.Evento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    EventoDTO toDto(Evento evento);

    List<EventoDTO> toDtoList(List<Evento> eventos);

    Evento toEntity(EventoDTO eventoDTO);

    List<Evento> toEntityList(List<EventoDTO> eventoDTOs);

}
