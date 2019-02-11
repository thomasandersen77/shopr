package io.shopr.product;

import io.shopr.ShoprApplication;
import io.shopr.dto.ProductListDto;
import io.shopr.repositories.domain.Category;
import io.shopr.repositories.domain.Product;
import io.shopr.testutils.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.Objects;

import static org.junit.Assert.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ShoprApplication.class, TestConfig.class})
@Transactional
public class ProductControllerTest {

    @Autowired TestRestTemplate template;
    @Autowired EntityManagerFactory entityManagerFactory;
    private TestEntityManager em;

    @BeforeEach
    public void configure() {
        em = new TestEntityManager(entityManagerFactory);
    }

    @Test
    public void createProduct() {
        var response = template.postForEntity(
                "/product",
                new Product("Epler", 15.00, new Category("Frukt"), 6),
                Long.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() != null && response.getBody().intValue() > 0L);

        var product = em.getEntityManager().find(Product.class, response.getBody());
        assertNotNull(product);
        assertEquals("Epler", product.getName());
    }

    @Test
    public void getProductList() {
        if(!em.getEntityManager().getTransaction().isActive())
            em.getEntityManager().getTransaction().begin();

        for (int i = 0; i < 50; i++) {
            em.persist(new Product(i + " $", i * 2, new Category("cat-" + i), 1));
        }
        em.getEntityManager().getTransaction().commit();

        var response = template.getForEntity("/product", ProductListDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(50 <= Objects.requireNonNull(response.getBody()).getProducts().size());
    }


    @Test
    public void getProductById() {
        var id = em.persistAndFlush(new Product("test", 100, new Category("category"), 1)).getId();
        em.getEntityManager().getTransaction().commit();

        var response = template.getForEntity("/product/{id}", Product.class, id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        var product = response.getBody();
        assertNotNull(product);
        assertEquals(product.getId().intValue(), id.intValue());
        assertEquals("test", product.getName());
        assertEquals("category", product.getCategory().getName());
    }
}