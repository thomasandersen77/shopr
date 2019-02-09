package io.shopr.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Component
@RestControllerAdvice
public class ResponsStatusExceptionAdvice {

    @Around("execution(* io.shopr.controllers.*.*(..))")
    public Object translateToResponseException(ProceedingJoinPoint joinPoint){
        Object retVal;
        try {
            retVal = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage(), throwable);
        }
        return retVal;
    }
}
