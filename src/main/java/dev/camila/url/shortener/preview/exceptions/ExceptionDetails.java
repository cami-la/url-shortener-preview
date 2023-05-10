package dev.camila.url.shortener.preview.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public record ExceptionDetails(
    String title,
    LocalDateTime timestamp,
    int status,
    String exception,
    Map<String, String> details
) {
}
