package br.com.apirestmuralvagas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNaoEncontraexception extends RuntimeException {
    public UsuarioNaoEncontraexception(String mensagem) {

        super(mensagem);
    }
}