package io.shopr.testutils;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.Objects;

//@AutoConfigureTestEntityManager
@Configuration
@ConditionalOnClass({ EntityManagerFactory.class })
public class TestConfig {
    @Bean
    public TestdataManager testdataManager(EntityManagerFactory emf) {
        Objects.requireNonNull(emf, "EntityManagerFactory bean must be set");
        return new TestdataManager(emf);
    }
}
