package io.shopr.testutils;

import io.shopr.common.ResponsStatusExceptionAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@Configuration
public class TestConfig {
    @Bean
    public TestBeanDefinitionRegistryPostProcessor postProcessor() {
        return new TestBeanDefinitionRegistryPostProcessor();
    }

    @Primary
    @Bean
    @ConditionalOnMissingBean
    public MockMvc mockMvc() {
        return MockMvcBuilders.standaloneSetup(ResponsStatusExceptionAdvice.class).build();
    }
}
