package br.com.bgrbarbosa.eventos_api.controller.mapper;



import br.com.bgrbarbosa.eventos_api.controller.dto.EventoDTO;
import br.com.bgrbarbosa.eventos_api.model.Evento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    EventoDTO toDto(Evento evento);

    List<EventoDTO> toDtoList(List<Evento> eventos);

    Evento toEntity(EventoDTO eventoDTO);

    List<Evento> toEntityList(List<EventoDTO> eventoDTOs);

}
