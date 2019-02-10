package io.shopr.common;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Component
public class ResponsStatusExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(ResponsStatusExceptionAdvice.class);

    @AfterThrowing(throwing = "exception", pointcut =
            "execution(* io.shopr..*Controller.*(..))"
    )
    public void translateToResponseException(Exception exception) {
        if (exception instanceof ResponseStatusException) {
            throw (ResponseStatusException) exception;
        }

        if (exception instanceof ShoprException) {
            ShoprException shoprException = (ShoprException) exception;
        }

        log.error("An Unexpected Exception Was Raised. Stacktrace: ", exception);
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());

    }
}
