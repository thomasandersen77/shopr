package io.shopr.repositories;

import io.shopr.entities.Category;
import io.shopr.entities.ProductItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductItemRepositoryTest {

    @Autowired
    ProductRepository repository;

    @Test
    public void saveCategoryAndProduct() {
        ProductItem productItem = new ProductItem();
        productItem.setName("car");
        productItem.setCategory(new Category("AUTOMOBILES"));
        productItem.setPrice(98.90);
        productItem = repository.save(productItem);
        assertTrue(repository.save(productItem).getId() > 0);
        assertTrue(repository.save(new ProductItem()).getId() > 1);
        assertTrue(repository.save(new ProductItem()).getId() > 2);
        assertTrue(repository.save(new ProductItem()).getId() > 3);
        assertNotNull(productItem);
        assertEquals("car", productItem.getName());
        assertEquals(98.90, productItem.getPrice(), 0);
    }
}