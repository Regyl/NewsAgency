package org.idk.newsagency.api.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handleRuntimeException(RuntimeException e) {
        Map<String, Object> body = new HashMap<>(3);
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());
        body.put("cause", e.getCause());
        return body;
    }
}
