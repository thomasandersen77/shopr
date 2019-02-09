package io.shopr.cart;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@Ignore
@RunWith(SpringRunner.class)
@DataJpaTest
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