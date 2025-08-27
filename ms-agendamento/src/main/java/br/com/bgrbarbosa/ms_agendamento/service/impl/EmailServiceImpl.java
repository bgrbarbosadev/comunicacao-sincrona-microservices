package br.com.bgrbarbosa.ms_agendamento.service.impl;

import br.com.bgrbarbosa.ms_agendamento.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void enviarEmail(String para, String assunto, String texto) {

        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom("bgrbarbosa@gmail.com");
        mensagem.setTo(para);
        mensagem.setSubject(assunto);
        mensagem.setText(texto);
        mailSender.send(mensagem);
    }
}
