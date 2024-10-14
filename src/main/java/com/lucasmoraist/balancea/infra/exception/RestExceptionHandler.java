package com.lucasmoraist.balancea.infra.exception;

import com.lucasmoraist.balancea.exceptions.DuplicateBadgetException;
import com.lucasmoraist.balancea.exceptions.ExceptionDTO;
import com.lucasmoraist.balancea.exceptions.InvalidDateException;
import com.lucasmoraist.balancea.exceptions.ResourceNotFoundException;
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
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<List<DataValidation>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors()
                .stream()
                .map(DataValidation::new)
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DuplicateBadgetException.class)
    protected ResponseEntity<ExceptionDTO> handleDuplicateBadgetException(DuplicateBadgetException e) {
        return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(InvalidDateException.class)
    protected ResponseEntity<ExceptionDTO> handleInvalidDateException(InvalidDateException e) {
        return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ExceptionDTO> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<String> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<String> handleAuthenticationException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<String> handleAccessDeniedException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionDTO> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private record DataValidation(String campo, String mensagem) {
        public DataValidation(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }


}
