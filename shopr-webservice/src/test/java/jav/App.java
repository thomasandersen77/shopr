package jav;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableRestRepo(id = "test", entity = GenericType.class)
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class);
        GenericRepository bean = context.getBean(GenericRepository.class);
        System.out.println(bean);

    }
}
