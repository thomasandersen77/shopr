package io.shopr.controllers;

import io.shopr.ShoprApplication;
import io.shopr.entities.Category;
import io.shopr.entities.ProductItem;
import io.shopr.testutils.EnableIntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ShoprApplication.class)
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
public class ProductItemControllerTest {

    @Autowired
    TestRestTemplate template;
    @Autowired
    TestEntityManager em;

    @Test
    @Transactional
    public void createProduct() {
        ResponseEntity<Long> response = template.postForEntity("/product",
                new ProductItem("Epler", 15.00, new Category("Frukt"), 6),
                Long.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() != null && response.getBody().intValue() > 0L);

        ProductItem productItem = em.find(ProductItem.class, response.getBody());
        assertNotNull(productItem);
        assertEquals("Epler", productItem.getName());
    }

    @Test
    @Transactional
    public void getProductList() {
        em.persist(new ProductItem());
        em.persist(new ProductItem());
        em.persist(new ProductItem());
        em.persist(new ProductItem());
        em.persist(new ProductItem());
        em.getEntityManager().getTransaction().commit();

        ResponseEntity<ProductList> response = template.getForEntity("/product", ProductList.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(5 <= response.getBody().getProductItems().size());
    }


    @Test
    @Transactional
    public void getProductById() {
        Long id = em.persistAndFlush(new ProductItem("test", 100, new Category("category"))).getId();
        em.getEntityManager().getTransaction().commit();

        ResponseEntity<ProductItem> response = template.getForEntity("/product/{id}", ProductItem.class, id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ProductItem productItem = response.getBody();
        assertNotNull(productItem);
        assertEquals(productItem.getId().intValue(), id.intValue());
        assertEquals("test", productItem.getName());
        assertEquals("category", productItem.getCategory().getName());
    }
}