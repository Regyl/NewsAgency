package com.deepspace.newsagency.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Aspect
@Component
public class LoggerAdvice {

    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
    }

    /*@Pointcut("execution(* org.springframework.security.authentication.AuthenticationManager.authenticate(..))")
    public void loginHandler() {}*/

    @Pointcut("@annotation(com.deepspace.newsagency.annotation.Loggable)")
    public void loggableMethod() {}

    @Pointcut("within(@com.deepspace.newsagency.annotation.Loggable *)")
    public void loggableClass() {}


    @Around("loggableMethod() || loggableClass()")
    public Object applicationLogger(ProceedingJoinPoint point) throws Throwable {
        String fullName = obtainFullName(point, null);
        String requestId = UUID.randomUUID().toString().substring(30);

        Object[] args = point.getArgs();
        log.info(requestId + "-START: " + fullName + ". Args: " + MAPPER.writeValueAsString(args));

        return point.proceed();
        /*Object object = point.proceed();
        log.info(requestId + "-END: " + fullName + " Response: " + MAPPER.writeValueAsString(object));

        return object;*/
    }

    /*@Before("loginHandler()")
    public void loggingSecurity(JoinPoint point) throws JsonProcessingException {
        Object[] args = point.getArgs();
        String name = ((Authentication) args[0]).getName();
        String fullName = obtainFullName(point, name);
        String requestId = UUID.randomUUID().toString().substring(30);

        log.info(requestId + ": " + fullName + ". Args: " + MAPPER.writeValueAsString(args));
    }*/

    private String obtainFullName(JoinPoint point, @Nullable String username) {
        String methodName = point.getSignature().getName();
        String className = point.getTarget().getClass().toString().substring(6);
        String fullName = className + ":" + methodName + "()";

        return fullName;
    }



}
