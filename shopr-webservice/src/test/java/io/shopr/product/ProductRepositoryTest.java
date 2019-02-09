package io.shopr.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.shopr.model.Category;
import io.shopr.model.Product;
import io.shopr.product.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    @Test
    public void saveCategoryAndProduct() throws JsonProcessingException {
        Product product = new Product();
        product.setName("car");
        product.setCategory(new Category("AUTOMOBILES"));
        product.setPrice(98.90);
        product = repository.save(product);
        assertTrue(repository.save(product).getId() > 0);
        assertTrue(repository.save(new Product()).getId() > 1);
        assertTrue(repository.save(new Product()).getId() > 2);
        assertTrue(repository.save(new Product()).getId() > 3);
        assertNotNull(product);
        assertEquals("car", product.getName());
        assertEquals(98.90, product.getPrice(), 0);
        System.err.println( new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(product) );
    }
}