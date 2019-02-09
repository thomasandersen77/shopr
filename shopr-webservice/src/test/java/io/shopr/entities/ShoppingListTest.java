package io.shopr.entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;

import static java.util.Comparator.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShoppingListTest {

    @Autowired
    TestEntityManager em;

    @Test
    public void update_timestamp_on_save() {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.addProduct(new Product("test1", 1, new Category("cat1")));
        shoppingList.addProduct(new Product("test2", 2, new Category("cat2")));
        shoppingList.addProduct(new Product("test3", 3, new Category("cat3")));

        shoppingList = em.persistAndFlush(shoppingList);
        ShoppingList shoppingListPersisted = em.find(ShoppingList.class, shoppingList.getId());
        assertEquals(shoppingListPersisted.getId(), shoppingList.getId());

        shoppingListPersisted.getProducts()
                .stream()
                .sorted(comparing(Product::getId))
                .forEach(product -> {
                    System.out.println(product);
                });
    }
}