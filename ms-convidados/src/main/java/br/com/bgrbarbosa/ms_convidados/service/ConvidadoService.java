package br.com.bgrbarbosa.ms_convidados.service;

import br.com.bgrbarbosa.ms_convidados.model.Convidado;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ConvidadoService{

    List<Convidado>findAll();

    Optional<Convidado> findById(UUID id);

    Optional<Convidado> findByCpf(String cpf);

    Convidado insert(Convidado convidado) throws Exception;

    Convidado updateConvidado(Convidado convidado) throws Exception;

    void delete(UUID id) throws Exception;

}
