package br.com.bgrbarbosa.ms_convidados.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "tb_convidado",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "cpf_convidado")
        })
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Convidado {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_convidado")
    private UUID idConvidado;

    @Column(name = "nome_convidado", length = 100)
    private String nomeConvidado;

    @Column(name = "cpf_convidado", length = 14, unique = true)
    private String cpfConvidado;

    @Column(name = "tel_convidado", length = 11)
    private String telConvidado;

    @Column(name = "email_convidado", length = 100, unique = true)
    private String emailConvidado;

}
