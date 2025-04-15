package campina.retifica.api_rest.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<NullFieldsResponse>> handlingNullFieldsException(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(NullFieldsResponse::new).toList());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<UnauthorizedResponse> handlingIncorrectCredentials(BadCredentialsException exception) {
        return new ResponseEntity<UnauthorizedResponse>(new UnauthorizedResponse("Credenciais inválidas."), HttpStatus.UNAUTHORIZED);
    }

    private record NullFieldsResponse(String field, String message) {
        public NullFieldsResponse(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    private record UnauthorizedResponse(String message) {}
}
