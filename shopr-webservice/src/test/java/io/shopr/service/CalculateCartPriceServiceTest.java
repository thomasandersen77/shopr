package io.shopr.service;

import io.shopr.repositories.api.CartRepository;
import io.shopr.repositories.api.CustomerRepository;
import io.shopr.repositories.domain.Cart;
import io.shopr.repositories.domain.Category;
import io.shopr.repositories.domain.Customer;
import io.shopr.repositories.domain.Product;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
public class CalculateCartPriceServiceTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    CalculateCartPriceService calculateCartPriceService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;

    @Test
    @Transactional
    public void createCartAndCalculateSum() {
        assertNotNull(em);
        assertNotNull(calculateCartPriceService);

        Customer customer = new Customer(1, "Thomas", "Andersen", LocalDate.of(1977, 7, 9));
        em.persist(customer);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.addProduct(new Product("test1", 13.0, new Category("cat1"), 10));
        cart.addProduct(new Product("test2", 6.0, new Category("cat2"), 5));
        cart.addProduct(new Product("test3", 12.0, new Category("cat3"), 2));
        cart.addProduct(new Product("test4", 43.0, new Category("cat4"), 1));
        cartRepository.save(cart);

        double cartTotal = calculateCartPriceService.getCartTotal(customer);
        assertThat(cartTotal).isCloseTo(200.0, Percentage.withPercentage(20));

    }
}