package io.shopr;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ShoprApplication.class})
public class ShoprApplicationTests {

    @Test
    public void contextLoads() {
        assertTrue(true);
    }

}

