package io.shopr.configuration;

import io.shopr.common.RequestLoggingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(@Autowired RequestLoggingFilter requestLoggingFilter) {
        var filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(requestLoggingFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
