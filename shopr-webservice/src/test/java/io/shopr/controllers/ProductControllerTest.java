package io.shopr.controllers;

import io.shopr.ShoprApplication;
import io.shopr.controllers.transferobjects.ProductListDto;
import io.shopr.entities.Category;
import io.shopr.entities.Product;
import io.shopr.testutils.TestConfig;
import io.shopr.testutils.TestdataManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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

import javax.persistence.EntityManagerFactory;
import java.util.Objects;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ShoprApplication.class, TestConfig.class})
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
public class ProductControllerTest {

    @Autowired TestRestTemplate template;
    @Autowired EntityManagerFactory entityManagerFactory;
    private TestEntityManager em;

    @Before
    public void configure() {
        em = new TestEntityManager(entityManagerFactory);
    }

    @Test
    public void createProduct() {
        ResponseEntity<Long> response = template.postForEntity("/product",
                new Product("Epler", 15.00, new Category("Frukt"), 6),
                Long.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() != null && response.getBody().intValue() > 0L);


        Product product = em.getEntityManager().find(Product.class, response.getBody());
        assertNotNull(product);
        assertEquals("Epler", product.getName());
    }

    @Test
    public void getProductList() {
        for (int i = 0; i < 50; i++) {
            //em.getEntityManager().getTransaction().begin();
            em.persist(new Product());

        }
        em.getEntityManager().getTransaction().commit();
        ResponseEntity<ProductListDto> response = template.getForEntity("/product", ProductListDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(50 <= Objects.requireNonNull(response.getBody()).getProducts().size());
    }


    @Test
    public void getProductById() {
        Long id = em.persistAndFlush(new Product("test", 100, new Category("category"))).getId();

        ResponseEntity<Product> response = template.getForEntity("/product/{id}", Product.class, id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Product product = response.getBody();
        assertNotNull(product);
        assertEquals(product.getId().intValue(), id.intValue());
        assertEquals("test", product.getName());
        assertEquals("category", product.getCategory().getName());
    }
}