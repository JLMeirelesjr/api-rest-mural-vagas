package br.com.apirestmuralvagas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Trata recursos não encontrados (HTTP 404 - Not Found)
    @ExceptionHandler({UsuarioNaoEncontraexception.class, VagaNaoEncontraexception.class})
    public ResponseEntity<Map<String, Object>> handleNotFoundException(RuntimeException ex) {
        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("timestamp", LocalDateTime.now());
        resposta.put("status", HttpStatus.NOT_FOUND.value());
        resposta.put("erro", "Recurso Não Encontrado");
        resposta.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    // 2. Trata violações de regras de negócio (HTTP 400 - Bad Request)
    // Exemplo: tentar cadastrar e-mail duplicado ou recrutador inexistente
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("timestamp", LocalDateTime.now());
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erro", "Requisição Inválida");
        resposta.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    // 3. Trata falhas nas validações de atributos com @Valid (HTTP 400 - Bad Request)
    // Exemplo: campo @NotBlank vazio ou @Email em formato incorreto
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("timestamp", LocalDateTime.now());
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erro", "Erro de Validação nos Campos");

        // Mapeia cada campo com problema e sua respectiva mensagem de erro
        Map<String, String> errosCampos = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errosCampos.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        resposta.put("erros", errosCampos);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}