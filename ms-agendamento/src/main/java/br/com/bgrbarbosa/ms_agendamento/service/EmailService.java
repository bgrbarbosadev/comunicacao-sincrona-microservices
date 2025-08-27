package br.com.bgrbarbosa.ms_agendamento.service;

public interface EmailService {

    public void enviarEmail(String para, String assunto, String texto);
}
