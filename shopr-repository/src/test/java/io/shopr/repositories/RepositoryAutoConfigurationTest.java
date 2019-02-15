package io.shopr.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RepositoryAutoConfiguration.class)
@EnableJpaRepositories(basePackages = "io.shopr.repositories")
public class RepositoryAutoConfigurationTest {

    @Autowired
    ApplicationContext context;
/*    @Autowired
    JpaRepository jpaRepository;*/

    @Test
    void name() {

        assertNotNull(context.getBean(JpaRepository.class));

    }
}