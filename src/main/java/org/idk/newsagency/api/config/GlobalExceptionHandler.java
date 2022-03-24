package org.idk.newsagency.api.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    /*@ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
    public Map<String, Object> handleRuntimeException(RuntimeException e) {
        Map<String, Object> body = new HashMap<>(3);
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());
        return body;
    }*/
    
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handle(ExpiredJwtException e) {
        Map<String, Object> body = new HashMap<>(3);
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());
        return body;
    }
}
