package br.com.bgrbarbosa.ms_agendamento.service.impl;

import br.com.bgrbarbosa.ms_agendamento.model.Agendamento;
import br.com.bgrbarbosa.ms_agendamento.model.enums.Status;
import br.com.bgrbarbosa.ms_agendamento.repository.AgendamentoRepository;
import br.com.bgrbarbosa.ms_agendamento.service.AgendamentoService;
import br.com.bgrbarbosa.ms_agendamento.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private EmailService emailService;

    @Override
    public List<Agendamento> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Agendamento> findById(UUID id) {
        Optional<Agendamento> aux = repository.findById(id);
        return aux;
    }

    @Override
    public Agendamento save(Agendamento agendamento) {
        agendamento.setConfirmacao(Status.PENDENTE);
        Agendamento aux = repository.save(agendamento);
        enviar(agendamento, "Confirmação de Inscrição");
        return aux;
    }

    @Override
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public void enviar(Agendamento agendamento, String assunto) {
        String para = agendamento.getConvidado().getEmailConvidado();
        String nome = agendamento.getConvidado().getNomeConvidado();
        String texto = nome + ", seu agendamento foi realizado. Favor confirmar a sua participação até a data do evento";
        emailService.enviarEmail(para, assunto, texto);
    }
}
