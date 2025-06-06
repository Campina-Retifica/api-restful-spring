package br.edu.unifacisa.projeto_integrador.security.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Void> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataErrorValidation>> handleError400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(DataErrorValidation::new).toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleEntityNotFoundException(EntityNotFoundException ex){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionMessage> handleDuplicateResourceException(DuplicateResourceException ex){
        return new ResponseEntity<>(new ExceptionMessage(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InactiveCustomerException.class)
    public ResponseEntity<ExceptionMessage> handleInactiveCustomerException(InactiveCustomerException ex){
        return new ResponseEntity<>(new ExceptionMessage(ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public record DataErrorValidation(String field, String message) {
        public DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    public record ExceptionMessage(String message) {
        public ExceptionMessage(String message) {
            this.message = message;
        }
    }
}