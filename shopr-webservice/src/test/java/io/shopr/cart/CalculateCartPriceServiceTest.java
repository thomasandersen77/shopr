package io.shopr.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
public class CalculateCartPriceServiceTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    CalculateCartPriceService calculateCartPriceService;

    @Test
    public void createCartAndCalculateSum() {
        assertNotNull(em);
        assertNotNull(calculateCartPriceService);
    }
}