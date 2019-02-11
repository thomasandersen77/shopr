package jav;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@OverrideAutoConfiguration(enabled = false)
@ImportAutoConfiguration
@Import({RestResourceAutoConfiguration.class})
public @interface EnableRestRepo {
    Class<?> entity();
    String id();
}
