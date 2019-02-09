package io.shopr.testutils;

import io.shopr.category.CategoryController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.util.Comparator;
import java.util.stream.Stream;

//@Component
public class TestBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(TestBeanDefinitionRegistryPostProcessor.class);

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        if(registry instanceof ConfigurableListableBeanFactory) {
            registry.removeBeanDefinition("categoryController");
            var beanDefinition = new RootBeanDefinition(CategoryController.class);
            beanDefinition.setPrimary(true);
            beanDefinition.setAutowireCandidate(true);
            registry.registerBeanDefinition("categoryController", beanDefinition);
        }
        Stream.of(registry.getBeanDefinitionNames())
                .sorted(Comparator.naturalOrder())
                .forEach(action -> log.trace(">>>> bean: [{}]", action));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.err.println();
    }
}
