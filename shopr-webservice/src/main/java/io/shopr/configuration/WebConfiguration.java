package io.shopr.configuration;

import io.shopr.common.RequestLoggingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class WebConfiguration {

    public WebConfiguration() {
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(@Autowired RequestLoggingFilter requestLoggingFilter) {
        var filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(requestLoggingFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
