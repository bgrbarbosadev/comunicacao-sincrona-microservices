package br.com.bgrbarbosa.ms_agendamento.controller.dto;

import br.com.bgrbarbosa.ms_agendamento.model.Agendamento;
import br.com.bgrbarbosa.ms_agendamento.model.Evento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConvidadoDTO{

    private UUID idConvidado;

    @NotBlank(message = "{not.blank.message}")
    @Size(min = 3, max = 60, message = "{size.message}")
    private String nomeConvidado;

    @NotBlank(message = "{not.blank.message}")
    @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$", message = "{invalid.cpf.message}")
    @CPF(message = "{invalid.cpf.message}")
    private String cpfConvidado;

    @NotBlank(message = "{not.blank.message}")
    @Size(min = 10, max = 13, message = "{size.message}")
    private String telConvidado;

    @Email(message = "{email.message}", regexp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$")
    private String emailConvidado;

    @JsonIgnore
    private List<Agendamento> agendamentos;


}


