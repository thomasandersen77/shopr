package jav;

import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

@Configuration
@AutoConfigureAfter(value = WebMvcAutoConfiguration.class)
@ConditionalOnClass({CrudRepository.class})
public class RestResourceAutoConfiguration implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        Reflections reflections = new Reflections("jav");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(EnableRestRepo.class);
        for (Class<?> c : annotated) {
            EnableRestRepo declaredAnnotation = c.getDeclaredAnnotation(EnableRestRepo.class);
            Class<?> entity = declaredAnnotation.entity();
            String id = declaredAnnotation.id();
            Supplier<GenericRepository> genericRepositorySupplier = () -> (GenericRepository) Proxy.newProxyInstance(
                    c.getClassLoader(),
                    new Class[]{GenericRepository.class},
                    new MyInvocationHandler(entity));
                beanDefinitionRegistry.registerBeanDefinition(id + "-" + UUID.randomUUID().toString(),
                        new RootBeanDefinition(GenericRepository.class, genericRepositorySupplier)
            );
        }
    }

    public static class MyInvocationHandler  implements InvocationHandler {
        private Object obj;
        public  MyInvocationHandler(Object obj) {
            this.obj = obj;
        }
        public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
            Object result;
            try{
                if(m.getName().contains("get")){
                    System.out.println("...get Method Executing...");
                }else{
                    System.out.println("...set Method Executing...");
                }
                result = m.invoke(obj, args);
            } catch (Exception e) {
                throw e;
            }
            return result;
        }
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        // n00p
    }

}

