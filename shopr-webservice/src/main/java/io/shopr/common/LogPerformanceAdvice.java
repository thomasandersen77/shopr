package io.shopr.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.UUID;

@Component
@Aspect
public class LogPerformanceAdvice {
    private static final Logger log = LoggerFactory.getLogger(LogPerformanceAdvice.class);

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)  ||" +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping)  ||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ")
    public Object methodTimer(ProceedingJoinPoint joinPoint){
        try {
            StopWatch stopWatch = new StopWatch(UUID.randomUUID().toString());
            stopWatch.start(Thread.currentThread().getName());
            Object retVal = joinPoint.proceed(joinPoint.getArgs());
            stopWatch.stop();
            log.info(">>> Request processed in {} millis for target = [{}] - (taskname = {})",
                    stopWatch.getLastTaskTimeMillis(),
                    joinPoint.getTarget().getClass().getName(),
                    stopWatch.getLastTaskName());
            return retVal;
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}
