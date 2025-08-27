package br.com.bgrbarbosa.ms_agendamento.repository;



import br.com.bgrbarbosa.ms_agendamento.model.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface ConvidadoRepository extends JpaRepository<Convidado, UUID>{

    Optional<Convidado> findByCpfConvidado(String cpf);
}   
