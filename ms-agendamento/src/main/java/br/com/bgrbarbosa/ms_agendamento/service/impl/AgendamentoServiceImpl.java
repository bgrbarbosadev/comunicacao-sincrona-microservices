package br.com.bgrbarbosa.ms_agendamento.service.impl;

import br.com.bgrbarbosa.ms_agendamento.model.Agendamento;
import br.com.bgrbarbosa.ms_agendamento.model.enums.Status;
import br.com.bgrbarbosa.ms_agendamento.repository.AgendamentoRepository;
import br.com.bgrbarbosa.ms_agendamento.service.AgendamentoService;
import br.com.bgrbarbosa.ms_agendamento.service.EmailService;
import br.com.bgrbarbosa.ms_agendamento.service.exception.ResourceNotFoundException;
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
        String MENSAGEM_CONFIRMACAO_INSCRICAO = ", seu agendamento foi realizado. Favor confirmar a sua participação até a data do evento";
        agendamento.setConfirmacao(Status.PENDENTE);
        Agendamento aux = repository.save(agendamento);
        enviar(agendamento, "Confirmação de Inscrição", MENSAGEM_CONFIRMACAO_INSCRICAO);
        return aux;
    }

    @Override
    public Agendamento update(Agendamento agendamento) {
        String MENSAGEM_CONFIRMACAO_INSCRICAO = ", seu agendamento foi alterado. Favor confirmar os dados e a sua participação até a data do evento";
        Agendamento aux = repository.findById(agendamento.getIdAgendamento()).orElseThrow(
                () -> new ResourceNotFoundException("Registro não encontrado!"));

        aux.setConfirmacao(agendamento.getConfirmacao());
        aux.setConvidado(agendamento.getConvidado());
        aux.setEvento(agendamento.getEvento());
        enviar(agendamento, "Alteração de Inscrição", MENSAGEM_CONFIRMACAO_INSCRICAO);
        return repository.save(agendamento);
    }

    @Override
    public void confirm(UUID uuid) {
        String MENSAGEM_CONFIRMACAO_INSCRICAO = ", sua presença foi confirmada. Favor levar um documento de identificação com foto no dia do evento";
        Agendamento aux = repository.findById(uuid).orElseThrow(
                () -> new ResourceNotFoundException("Registro não encontrado!"));
        aux.setConfirmacao(Status.CONFIRMADO);
        aux = repository.save(aux);
        enviar(aux, "Confirmação de participação", MENSAGEM_CONFIRMACAO_INSCRICAO);
    }

    @Override
    public void cancel(UUID uuid) {
        String MENSAGEM_CONFIRMACAO_INSCRICAO = ", sentimos muito!. Fica para uma próxima oportunidade!!";
        Agendamento aux = repository.findById(uuid).orElseThrow(
                () -> new ResourceNotFoundException("Registro não encontrado!"));
        aux.setConfirmacao(Status.CANCELADO);
        aux = repository.save(aux);
        enviar(aux, "Cancelamento de participação", MENSAGEM_CONFIRMACAO_INSCRICAO);
    }

    @Override
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public void enviar(Agendamento agendamento, String assunto, String mensagem) {
        String para = agendamento.getConvidado().getEmailConvidado();
        String nome = agendamento.getConvidado().getNomeConvidado();
        String texto = nome + mensagem;
        emailService.enviarEmail(para, assunto, texto);
    }
}
