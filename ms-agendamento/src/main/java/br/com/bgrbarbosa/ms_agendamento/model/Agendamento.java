package br.com.bgrbarbosa.ms_agendamento.model;

import br.com.bgrbarbosa.ms_agendamento.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_agendamento")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Agendamento {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_agendamento")
    private UUID idAgendamento;

    @Column
    private Status confirmacao;

    @Column
    private LocalDate dataIncricao;

    @Column
    private LocalDate dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "id_convidado", referencedColumnName = "idConvidado")
    private Convidado convidado;

    @ManyToOne
    @JoinColumn(name = "id_evento", referencedColumnName = "idEvento")
    private Evento evento;

    @PrePersist
    protected void onCreate() {
        dataIncricao = LocalDate.now();
        dataAtualizacao = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDate.now();
    }
}
