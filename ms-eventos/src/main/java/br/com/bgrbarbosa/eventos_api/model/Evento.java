package br.com.bgrbarbosa.eventos_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_evento")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Evento {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_evento")
    private UUID idEvento;

    @Column(name = "title_evento", length = 50)
    private String titleEvento;

    @Column(name = "desc_evento")
    private String descEvento;

    @Column(name = "dt_evento")
    private LocalDate dtEvento;

    @Column(name = "hr_evento")
    private Time hrEvento;

}
