package io.shopr.repositories;

import com.zaxxer.hikari.HikariDataSource;
import io.shopr.repositories.api.CustomerRepository;
import io.shopr.repositories.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@ConditionalOnClass({HikariDataSource.class})
@AutoConfigureTestEntityManager
@AutoConfigureAfter({TestEntityManagerAutoConfiguration.class})

@Configuration
public class RepositoryAutoConfiguration {
    @Bean
    public <T extends JpaRepository, R> T jpaRepository(@Autowired EntityManagerFactory emf) {
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{CustomerRepository.class},
                new Handler(new SimpleJpaRepository(Customer.class, new TestEntityManager(emf).getEntityManager())));
    }

    class Handler implements InvocationHandler {
        final SimpleJpaRepository original;

        Handler(SimpleJpaRepository original) {
            this.original = original;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Object invoke = method.invoke(original, args);
            return invoke;
        }
    }
}

