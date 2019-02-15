package io.shopr.testutils;

import io.shopr.repositories.RepositoryAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)

@Import(RepositoryAutoConfiguration.class)
public @interface EnableRepositories {
}
