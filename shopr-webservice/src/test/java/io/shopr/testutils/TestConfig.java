package io.shopr.testutils;

import io.shopr.common.ResponsStatusExceptionAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManagerFactory;
import java.util.Objects;

@Configuration
//@ConditionalOnClass({ EntityManagerFactory.class })
public class TestConfig {
    @Bean
    public TestdataManager testdataManager(EntityManagerFactory emf) {
        Objects.requireNonNull(emf, "EntityManagerFactory bean must be set");
        return new TestdataManager(emf);
    }

    @Bean
    public TestBeanDefinitionRegistryPostProcessor postProcessor(){
        return new TestBeanDefinitionRegistryPostProcessor();
    }

    @Primary
    @Bean
    @ConditionalOnMissingBean
    public MockMvc mockMvc() {
        return MockMvcBuilders.standaloneSetup(ResponsStatusExceptionAdvice.class).build();
    }
}
