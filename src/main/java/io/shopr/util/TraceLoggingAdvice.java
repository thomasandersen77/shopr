package io.shopr.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TraceLoggingAdvice {
    private static final Logger log = LoggerFactory.getLogger(TraceLoggingAdvice.class);

    @Before("execution(* io.shopr..*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info("**** calling [{} # {}()] *****", joinPoint.getTarget(), joinPoint.getSignature().getName());
    }

}
