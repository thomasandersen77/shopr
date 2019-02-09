package io.shopr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

import static java.util.stream.Stream.of;

@SpringBootApplication
@ServletComponentScan
public class ShoprApplication {
	private static final Logger log = LoggerFactory.getLogger(ShoprApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ShoprApplication.class, args);
		of(context.getEnvironment().getActiveProfiles())
				.forEachOrdered(profile -> log.info("Started application with profile = [{}]", profile));
	}
}

