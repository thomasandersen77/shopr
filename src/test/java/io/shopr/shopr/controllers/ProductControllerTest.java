package io.shopr.shopr.controllers;

import io.shopr.shopr.entities.Category;
import io.shopr.shopr.entities.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    TestRestTemplate template;

    @Test
    public void createProduct() {
        ResponseEntity<Long> response = template.postForEntity("/product", new Product("Epler", 15.00, new Category("Frukt"), 6), Long.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().intValue() > 0L);
    }

    @Test
    public void getProductList() {
    }

    @Test
    public void getProductById() {
    }
}