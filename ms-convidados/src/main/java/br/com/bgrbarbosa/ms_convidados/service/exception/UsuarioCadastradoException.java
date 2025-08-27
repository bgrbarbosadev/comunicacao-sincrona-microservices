package br.com.bgrbarbosa.ms_convidados.service.exception;

public class UsuarioCadastradoException extends RuntimeException {

    public UsuarioCadastradoException( String login ){
        super("Usuário já cadastrado para o login " + login);
    }
}
