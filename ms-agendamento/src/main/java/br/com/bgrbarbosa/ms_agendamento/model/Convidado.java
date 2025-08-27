package br.com.bgrbarbosa.ms_agendamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "tb_convidado")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Convidado {

    @Id
    private UUID idConvidado;

    @Column
    private String nomeConvidado;

    @Column
    private String cpfConvidado;

    @Column
    private String telConvidado;

    @Column
    private String emailConvidado;

    @OneToMany(mappedBy = "convidado", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Agendamento> agendamentos;


}
