package io.shopr.repositories;

import io.shopr.repositories.api.CartRepository;
import io.shopr.repositories.api.CustomerRepository;
import io.shopr.repositories.api.ProductRepository;
import io.shopr.repositories.domain.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(SpringExtension.class)
@EnableRepositorties
public class EnableRepositoriesTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CartRepository repository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ApplicationContext context;

    @Test
    void test_enable_annotation() {
        assertThat(repository);
        assertThat(productRepository);
        assertThat(customerRepository);
        assertThat(context);
    }
}
