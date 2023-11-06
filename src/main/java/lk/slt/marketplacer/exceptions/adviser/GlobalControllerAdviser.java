package lk.slt.marketplacer.exceptions.adviser;

import jakarta.servlet.ServletException;
import lk.slt.marketplacer.dto.ErrorResponse;
import lk.slt.marketplacer.exceptions.base.DuplicateRecordException;
import lk.slt.marketplacer.exceptions.base.RecordNotFoundException;
import lk.slt.marketplacer.exceptions.base.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerAdviser {
    @ExceptionHandler({
            DuplicateRecordException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> duplicateRecordException(SystemException ex) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(ex.getErrorCode())
                .message(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            RecordNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> notFoundException(SystemException ex) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(ex.getErrorCode())
                .message(ex.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            AccessDeniedException.class,
            AuthenticationException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handleAuthentication(Exception ex) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code("SYS-401")
                .message(ex.getMessage())
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(ErrorResponse.builder()
                .code("SYS-VALIDATION")
                .message("validation error")
                .errors(errors)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            MissingServletRequestPartException.class,
            HttpMediaTypeNotSupportedException.class
    })
    public ResponseEntity<ErrorResponse> handleServletExceptions(
            ServletException ex) {

        return new ResponseEntity<>(ErrorResponse.builder()
                .code("BAD-400")
                .message(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
