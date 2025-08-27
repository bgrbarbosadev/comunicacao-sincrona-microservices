package br.com.bgrbarbosa.ms_agendamento.service.impl;

import br.com.bgrbarbosa.ms_agendamento.model.Convidado;
import br.com.bgrbarbosa.ms_agendamento.repository.ConvidadoRepository;
import br.com.bgrbarbosa.ms_agendamento.service.ConvidadoService;
import br.com.bgrbarbosa.ms_agendamento.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ConvidadoServiceImpl implements ConvidadoService {

    @Autowired
    ConvidadoRepository repository;

    @Override
    public List<Convidado> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Convidado> findById(UUID uuid) {
        Optional<Convidado> aux =  repository.findById(uuid);
        if (!aux.isPresent()) {
            throw new ResourceNotFoundException("Entity not found: " + uuid);
        }
        return aux;
    }

    @Override
    public Optional<Convidado> findByCpf(String cpf) {
        Optional<Convidado> aux =  repository.findByCpfConvidado(cpf);
        if (!aux.isPresent()) {
            throw new ResourceNotFoundException("Entity not found: " + cpf);
        }
        return aux;
    }

    @Override
    public void insert(Convidado convidado){
        repository.save(convidado);
    }

    @Override
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

}
