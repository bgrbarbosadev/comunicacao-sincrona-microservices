package br.com.bgrbarbosa.ms_agendamento.controller.mapper;

import br.com.bgrbarbosa.ms_agendamento.controller.dto.ConvidadoDTO;
import br.com.bgrbarbosa.ms_agendamento.model.Convidado;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ConvidadoMapper {

    ConvidadoDTO toDto(Convidado convidado);

    List<ConvidadoDTO> toDtoList(List<Convidado> convidado);

    Convidado toEntity(ConvidadoDTO convidadoDTO);

    List<Convidado> toEntityList(List<ConvidadoDTO> convidadoDTOs);
}
