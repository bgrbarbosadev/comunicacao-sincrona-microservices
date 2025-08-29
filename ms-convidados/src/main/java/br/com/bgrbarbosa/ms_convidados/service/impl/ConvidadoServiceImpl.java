package br.com.bgrbarbosa.ms_convidados.service.impl;

import br.com.bgrbarbosa.ms_convidados.model.Convidado;
import br.com.bgrbarbosa.ms_convidados.repository.ConvidadoRepository;
import br.com.bgrbarbosa.ms_convidados.service.ConvidadoService;
import br.com.bgrbarbosa.ms_convidados.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ConvidadoServiceImpl implements ConvidadoService {

    private final ConvidadoRepository repository;

    private final RestTemplate restTemplate;

    private final String URL_CONVIDADO_AGENDA = "http://ms-agendamento/agendamento/convidado";

    @Override
    public List<Convidado> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Convidado> findById(UUID id) {
        Optional<Convidado> aux =  repository.findById(id);
        if (!aux.isPresent()) {
            throw new ResourceNotFoundException("Registro não encontrado: " + id);
        }
        return aux;
    }

    @Override
    public Optional<Convidado> findByCpf(String cpf) {
        Optional<Convidado> aux =  repository.findByCpfConvidado(cpf);
        if (!aux.isPresent()) {
            throw new ResourceNotFoundException("Registro não encontrado: " + cpf);
        }
        return aux;
    }

    @Override
    @Transactional
    public Convidado insert(Convidado convidado) throws Exception {
        Convidado aux = new Convidado();
        try {
            aux = repository.save(convidado);
            ResponseEntity<Convidado> response = restTemplate.postForEntity(
                    URL_CONVIDADO_AGENDA,
                    aux,
                    Convidado.class);
            return aux;
        } catch (Exception e) {
            repository.deleteById(aux.getIdConvidado());
            throw new Exception("Não foi possível inserir o convidado" + aux.getIdConvidado());
        }
    }

    @Override
    @Transactional
    public Convidado updateConvidado(Convidado convidado) throws Exception {
        Optional<Convidado> aux =  repository.findById(convidado.getIdConvidado());
        if (!aux.isPresent()) {
            throw new ResourceNotFoundException("Registro não encontrado: " + convidado.getIdConvidado());
        } else{
            Convidado convidadoAux =  repository.getOne(convidado.getIdConvidado());
            convidadoAux.setNomeConvidado(convidado.getNomeConvidado());
            convidadoAux.setEmailConvidado(convidado.getEmailConvidado());
            convidadoAux.setTelConvidado(convidado.getTelConvidado());
            convidadoAux.setCpfConvidado(convidado.getCpfConvidado());
            try {
                ResponseEntity<Convidado> response = restTemplate.postForEntity(
                        URL_CONVIDADO_AGENDA,
                        convidadoAux,
                        Convidado.class);
                repository.save(convidadoAux);
                return convidadoAux;
            } catch (Exception e) {
                throw new Exception("Não foi possível inserir o convidado" + convidado.getIdConvidado());
            }
        }
    }

    @Override
    public void delete(UUID uuid) throws Exception {
        if (!repository.existsById(uuid)) {
            throw new ResourceNotFoundException("Registro não encontrado" + uuid);
        }
        try {
            String urlDelecao = URL_CONVIDADO_AGENDA + "/" + uuid;
            restTemplate.delete(urlDelecao,uuid);
            repository.deleteById(uuid);
        } catch (Exception e) {
            throw new Exception("Não foi possível inserir o convidado" + uuid);
        }
    }

}
