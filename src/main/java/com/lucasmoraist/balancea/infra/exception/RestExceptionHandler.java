package com.lucasmoraist.balancea.infra.exception;

import com.lucasmoraist.balancea.exceptions.DuplicateBadgetException;
import com.lucasmoraist.balancea.exceptions.ExceptionDTO;
import com.lucasmoraist.balancea.exceptions.InvalidDateException;
import com.lucasmoraist.balancea.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    // MethodArgumentNotValidException
    // DuplicateBadgetException
    // InvalidDateException

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<List<DataValidation>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors()
                .stream()
                .map(DataValidation::new)
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    protected ResponseEntity<ExceptionDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
//        return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST));
//    }

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

    private record DataValidation(String campo, String mensagem) {
        public DataValidation(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }


}
