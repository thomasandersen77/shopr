package io.shopr.testutils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityTransaction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Aspect
public class CommitOnPersistTestdataAdvice {
    private static final Logger log = LoggerFactory.getLogger(CommitOnPersistTestdataAdvice.class);
    private TestdataManager testdataManager;

    @Autowired
    public CommitOnPersistTestdataAdvice(TestdataManager testdataManager) {
        this.testdataManager = testdataManager;
    }

    @Around("execution(* io.shopr.testutils.TestdataManager.*(..)) && !execution(* io.shopr.testutils.TestdataManager.get*(..)))")
    public Object commitOnPersist(ProceedingJoinPoint pjp){
        try {
            EntityTransaction transaction = testdataManager.getEntityManager().getTransaction();
            if(!transaction.isActive())
                transaction.begin();
            Object retVal = pjp.proceed(pjp.getArgs());
            log.info(":::> Commit testdata for target : [{}]. Arguments: [{}]",
                    pjp.getTarget().getClass().getSimpleName(),
                    arrayToString(pjp.getArgs()));
            transaction.commit();
            return retVal;
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    private String arrayToString(Object[] args) {
        return Stream.of(args).map(Object::toString).collect(Collectors.joining(","));
    }
}
