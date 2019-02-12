package io.shopr.configuration;

import io.shopr.ShoprApplication;
import io.shopr.repositories.api.CartRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx;
            ctx = new AnnotationConfigApplicationContext();
            ctx.register(ShoprApplication.class);
            ctx.refresh();

        CartRepository repository = ctx.getBean(CartRepository.class);
        assertThat(repository).isNotNull();
    }
}
