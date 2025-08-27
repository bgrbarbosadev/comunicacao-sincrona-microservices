package br.com.bgrbarbosa.eventos_api.repository;

import br.com.bgrbarbosa.eventos_api.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID> {
}
