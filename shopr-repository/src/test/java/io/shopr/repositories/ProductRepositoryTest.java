package io.shopr.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.shopr.repositories.api.ProductRepository;
import io.shopr.repositories.domain.Category;
import io.shopr.repositories.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProductRepositoryTest.class)
@EnableRepositories
@Configuration
public class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    @Test
    public void saveCategoryAndProduct() throws JsonProcessingException {
        Product product = new Product();
        product.setName("car");
        product.setCategory(new Category("AUTOMOBILES"));
        product.setPrice(98.90);
        product.setNumberOfItems(10);
        product = repository.save(product);
        assertTrue(repository.save(product).getId() > 0);
        assertTrue(repository.save(new Product("PC", 98.90, new Category(""), 1)).getId() > 1);
        assertTrue(repository.save(new Product("PC", 98.90, new Category(""), 1)).getId() > 2);
        assertTrue(repository.save(new Product("PC", 98.90, new Category(""), 1)).getId() > 3);
        assertNotNull(product);
        assertEquals("car", product.getName());
        assertEquals(98.90, product.getPrice(), 0);
    }
}