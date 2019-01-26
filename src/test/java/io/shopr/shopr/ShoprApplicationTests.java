package io.shopr.shopr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShoprApplication.class})
public class ShoprApplicationTests {

	@Test
	public void contextLoads() {
		assertTrue(true);
	}

}

