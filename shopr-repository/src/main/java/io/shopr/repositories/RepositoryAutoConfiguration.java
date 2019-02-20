package io.shopr.repositories;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
@AutoConfigureAfter(HibernateJpaAutoConfiguration.class)
public class RepositoryAutoConfiguration {
    public RepositoryAutoConfiguration() {
        System.err.println(":::::>>>> RepositoryAutoConfiguration ---->");
    }
}
