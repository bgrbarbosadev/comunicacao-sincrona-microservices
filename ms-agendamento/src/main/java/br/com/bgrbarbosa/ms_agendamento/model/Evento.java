package br.com.bgrbarbosa.ms_agendamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "tb_evento")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Evento {

    @Id
    private UUID idEvento;

    @Column
    private String titleEvento;

    @Column
    private String descEvento;

    @Column
    private LocalDate dtEvento;

    @Column
    private Time hrEvento;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Agendamento> agendamentos;

}
