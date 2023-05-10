package dev.camila.url.shortener.preview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ExceptionDetails> businessException(BusinessException ex) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(
            new ExceptionDetails(
                "Bad Request! Consult the documentation",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getClass().toString(),
                Map.of("Cause", ex.getMessage())
        ));
  }
}
