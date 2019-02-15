package io.shopr.repositories;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.reflections.Reflections;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@ConditionalOnClass({ EntityManagerFactory.class })
@AutoConfigureAfter(value = {DataSourceAutoConfiguration.class})
@Import(JpaBaseConfiguration.class)
@ComponentScan
public class RepositoryAutoConfiguration implements InitializingBean {

    final
    DataSource dataSource;
    final
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;

    @Autowired
    public RepositoryAutoConfiguration(HikariDataSource dataSource, LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        this.dataSource = dataSource;
        this.localContainerEntityManagerFactoryBean = localContainerEntityManagerFactoryBean;
    }

    @Override
    public void afterPropertiesSet() {
        EntityManagerFactory entityManagerFactory = this.localContainerEntityManagerFactoryBean.getObject();
        Assertions.assertNotNull(entityManagerFactory);

        Set<Object> entities = new HashSet<>();
        Set<Class<?>> classes = new Reflections("io.shopr").getTypesAnnotatedWith(Entity.class);
        classes.forEach(clazz -> {
            Stream.of(clazz.getDeclaredConstructors()).collect(Collectors.toList()).stream()
                    .filter(c -> c.getParameterCount() == 0)
                    .forEach(constructor -> {
                        try {
                            Object instance = clazz.cast(constructor.newInstance());
                            System.err.println(instance);
                            entities.add(instance);
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
        });
    }
}

