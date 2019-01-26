package io.shopr.shopr.controllers;

import io.shopr.shopr.testutils.IntegrationTest;
import io.shopr.shopr.entities.Category;
import io.shopr.shopr.entities.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@IntegrationTest
public class ProductControllerTest {

    @Autowired
    TestRestTemplate template;
    @Autowired
    TestEntityManager em;

    @Test
    public void createProduct() {
        ResponseEntity<Long> response = template.postForEntity("/product", new Product("Epler", 15.00, new Category("Frukt"), 6), Long.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() != null && response.getBody().intValue() > 0L);

        Product product = em.find(Product.class, response.getBody());
        assertNotNull(product);
        assertEquals("Epler", product.getName());

    }

    @Test
    public void getProductList() {
        assertNotNull(em);
    }

    @Test
    public void getProductById() {
    }
}