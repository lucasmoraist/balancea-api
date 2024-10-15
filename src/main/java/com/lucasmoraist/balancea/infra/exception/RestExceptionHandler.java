package com.lucasmoraist.balancea.infra.exception;

import com.lucasmoraist.balancea.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<List<DataValidation>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors()
                .stream()
                .map(DataValidation::new)
                .toList();

        log.warn("Erro de validação: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DuplicateBadgetException.class)
    protected ResponseEntity<ExceptionDTO> handleDuplicateBadgetException(DuplicateBadgetException e) {
        log.warn("Exceção de orçamento duplicado: {}", e.getMessage());
        return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(InvalidDateException.class)
    protected ResponseEntity<ExceptionDTO> handleInvalidDateException(InvalidDateException e) {
        log.warn("Data inválida: {}", e.getMessage());
        return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ExceptionDTO> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.warn("Recurso não encontrado: {}", e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<String> handleBadCredentialsException() {
        log.warn("Tentativa de login com credenciais inválidas.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<String> handleAuthenticationException() {
        log.warn("Falha na autenticação.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<String> handleAccessDeniedException() {
        log.warn("Acesso negado.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
    }

    @ExceptionHandler(EmailDuplicateException.class)
    protected ResponseEntity<ExceptionDTO> handleEmailDuplicateException(EmailDuplicateException e) {
        log.warn("Email duplicado: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionDTO(e.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(CredentialsException.class)
    protected ResponseEntity<ExceptionDTO> handleCredentialsException(CredentialsException e) {
        log.warn("Credenciais inválidas: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionDTO(e.getMessage(), HttpStatus.UNAUTHORIZED));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionDTO> handleException(Exception e) {
        log.error("Ocorreu um erro inesperado: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }


    private record DataValidation(String campo, String mensagem) {
        public DataValidation(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }


}
