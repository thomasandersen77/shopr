package io.shopr.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
@EnableJpaRepositories
public class RepositoryConfigurationSupport {

}
