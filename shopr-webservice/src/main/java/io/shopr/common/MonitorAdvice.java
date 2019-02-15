package io.shopr.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Aspect
public class MonitorAdvice {

    @After("execution(* io.shopr.controllers.*Controller.new(..))")
    public void monitor(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(pjp.getSignature().getDeclaringType() +""+ pjp.getSignature().getName());
        Object retval = pjp.proceed(arrayToString(pjp.getArgs()));

        //return retval;
    }

    private Object[] arrayToString(Object[] args) {
        return Stream.of(args).peek(System.out::println).collect(Collectors.toList()).toArray();
    }
}
