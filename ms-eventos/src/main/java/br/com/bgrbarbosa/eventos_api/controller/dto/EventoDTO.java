package br.com.bgrbarbosa.eventos_api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventoDTO {

    private UUID idEvento;

    @NotEmpty(message = "Titulo não pode ser vazio")
    @Size(min = 3, max = 40, message = "Titulo deve conter entre 3 a 40 caracteres")
    private String titleEvento;

    @NotEmpty(message = "Titulo não pode ser vazio")
    private String descEvento;

    @NotNull(message = "Data não pode ser nula")
    private LocalDate dtEvento;

    @NotNull(message = "Horário não pode ser nulo")
    private Time hrEvento;
}
