package com.lucasmoraist.balancea.exceptions;

import org.springframework.http.HttpStatus;

public record ExceptionDTO(String message, HttpStatus status) {
}
