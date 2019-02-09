package io.shopr.testutils;

import io.shopr.controllers.CategoryController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.stream.Stream;

//@Component
public class TestBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        if(registry instanceof ConfigurableListableBeanFactory) {
            registry.removeBeanDefinition("categoryController");
            RootBeanDefinition beanDefinition = new RootBeanDefinition(CategoryController.class);
            beanDefinition.setPrimary(true);
            beanDefinition.setAutowireCandidate(true);
            registry.registerBeanDefinition("categoryController", beanDefinition);
        }
        Stream.of(registry.getBeanDefinitionNames())
                .sorted(Comparator.naturalOrder())
                .forEach(System.err::println);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.err.println();
    }
}
