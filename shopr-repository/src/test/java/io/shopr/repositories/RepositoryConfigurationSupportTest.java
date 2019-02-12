package io.shopr.repositories;

import io.shopr.repositories.api.CartRepository;
import io.shopr.repositories.api.CategoryRepository;
import io.shopr.repositories.api.ProductRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RepositoryConfigurationSupport.class)
public class RepositoryConfigurationSupportTest {

    @Autowired
    ApplicationContext context;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void contextLoads() {
        assertNotNull(context);
        assertNotNull(cartRepository);
        assertNotNull(categoryRepository);
        assertNotNull(productRepository);
    }

}

