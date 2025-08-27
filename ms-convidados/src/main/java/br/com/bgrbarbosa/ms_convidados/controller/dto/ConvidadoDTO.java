package br.com.bgrbarbosa.ms_convidados.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConvidadoDTO{

    private UUID idConvidado;

    @NotEmpty(message = "Nome não pode ser vazio")
    @Size(min = 3, max = 60, message = "Titulo deve conter entre 3 a 60 caracteres")
    private String nomeConvidado;

    @NotEmpty(message = "CPF não pode ser vazio")
    @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$", message = "CPF inválido")
    @CPF(message = "CPF inválido")
    private String cpfConvidado;

    @NotEmpty(message = "Telefone não pode ser vazio")
    @Size(min = 10, max = 13, message = "Telefone deve conter entre 10 a 13 caracteres")
    private String telConvidado;

    @Email(message = "Email inválido", regexp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$")
    private String emailConvidado;

}


