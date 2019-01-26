package io.shopr.shopr.controllers;

import io.shopr.shopr.entities.Category;
import io.shopr.shopr.entities.Product;
import io.shopr.shopr.testutils.IntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@IntegrationTest
public class ProductControllerTest {

    @Autowired
    TestRestTemplate template;
    @Autowired
    TestEntityManager em;

    @Test
    @Transactional
    public void createProduct() {
        ResponseEntity<Long> response = template.postForEntity("/product", new Product("Epler", 15.00, new Category("Frukt"), 6), Long.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() != null && response.getBody().intValue() > 0L);

        Product product = em.find(Product.class, response.getBody());
        assertNotNull(product);
        assertEquals("Epler", product.getName());
    }

    @Test
    @Transactional
    public void getProductList() {
        em.persist(new Product());
        em.persist(new Product());
        em.persist(new Product());
        em.persist(new Product());
        em.persist(new Product());
        em.getEntityManager().getTransaction().commit();

        ResponseEntity<ProductList> response = template.getForEntity("/product", ProductList.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(5 <= response.getBody().getProducts().size());
    }


    @Test
    @Transactional
    public void getProductById() {
        Long id = em.persistAndFlush(new Product("test", 100, new Category("category"))).getId();
        em.getEntityManager().getTransaction().commit();

        ResponseEntity<Product> response = template.getForEntity("/product/{id}", Product.class, id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Product product = response.getBody();
        assertNotNull(product);
        assertEquals(product.getId().intValue(), id.intValue());
        assertEquals("test", product.getName());
        assertEquals("category", product.getCategory().getName());
    }
}