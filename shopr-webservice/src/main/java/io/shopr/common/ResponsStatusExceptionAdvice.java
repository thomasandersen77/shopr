package io.shopr.common;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Component
public class ResponsStatusExceptionAdvice {

    @AfterThrowing(throwing = "ex", pointcut =
            "execution(* io.shopr.controllers..*(..))"
    )
    public void translateToResponseException(ShoprException ex) {
        if(ex.getType() == ShoprException.Type.SERVER_ERROR) {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }

        if(ex.getType() == ShoprException.Type.CLIENT_ERROR) {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
