package io.shopr.repositories;

import io.shopr.repositories.domain.Cart;
import io.shopr.repositories.domain.Category;
import io.shopr.repositories.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Comparator.comparing;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = RepositoryConfigurationSupport.class)
public class CartRepositoryTest {

    @Autowired
    TestEntityManager em;

    @Test
    public void update_timestamp_on_save() {
        var cart = new Cart();
        var updateTimestamp = cart.getUpdateTimestamp();
        cart.addProduct(new Product("test1", 1, new Category("cat1")));
        cart.addProduct(new Product("test2", 2, new Category("cat2")));
        cart.addProduct(new Product("test3", 3, new Category("cat3")));

        cart = em.persistAndFlush(cart);
        assertThat(cart.getUpdateTimestamp()).isEqualByComparingTo(updateTimestamp);

        var cartPersisted = em.find(Cart.class, cart.getId());
        assertThat(cartPersisted.getId()).isEqualByComparingTo(cart.getId());

        cartPersisted.getProducts()
                .stream()
                .sorted(comparing(Product::getPrice))
                .forEach(price -> assertThat(price.getPrice()).isBetween(1.0, 3.0));
    }

}