package br.com.bgrbarbosa.ms_convidados.service.exception;

public class UserException extends RuntimeException {

    public UserException(String login ){
        super("Usuário já cadastrado para o login " + login);
    }
}
