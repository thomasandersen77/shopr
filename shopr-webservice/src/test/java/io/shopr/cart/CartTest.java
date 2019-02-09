package io.shopr.cart;

import io.shopr.model.Cart;
import io.shopr.model.Category;
import io.shopr.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Comparator.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CartTest {

    @Autowired
    TestEntityManager em;

    @Test
    public void update_timestamp_on_save() {
        var shoppingList = new Cart();
        shoppingList.addProduct(new Product("test1", 1, new Category("cat1")));
        shoppingList.addProduct(new Product("test2", 2, new Category("cat2")));
        shoppingList.addProduct(new Product("test3", 3, new Category("cat3")));

        shoppingList = em.persistAndFlush(shoppingList);
        var shoppingListPersisted = em.find(Cart.class, shoppingList.getId());
        assertEquals(shoppingListPersisted.getId(), shoppingList.getId());

        shoppingListPersisted.getProducts()
                .stream()
                .sorted(comparing(Product::getId))
                .forEach(System.err::println);
    }
}