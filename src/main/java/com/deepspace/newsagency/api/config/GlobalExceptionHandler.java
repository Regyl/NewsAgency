package com.deepspace.newsagency.api.config;

import com.deepspace.newsagency.exception.EntityAlreadyExistsException;
import io.jsonwebtoken.ExpiredJwtException;
import com.deepspace.newsagency.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handle(ExpiredJwtException e) {
        return obtainBody(e);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(EntityAlreadyExistsException e) {
        return obtainBody(e);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handle(EntityNotFoundException e) {
        return obtainBody(e);
    }

    private static Map<String, Object> obtainBody(RuntimeException e) {
        Map<String, Object> body = new HashMap<>(3);
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());
        return body;
    }
}
