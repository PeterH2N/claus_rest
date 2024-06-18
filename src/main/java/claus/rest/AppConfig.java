package claus.rest;

import jakarta.servlet.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.catalina.filters.CorsFilter;

import java.io.IOException;

@Configuration
public class AppConfig
{
    @Bean
    public FilterRegistrationBean<CorsFilter> someFilterRegistration() {

        FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CorsFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("cors.allowed.origins", "*");
        registration.setName("CorsFilter");
        registration.setOrder(1);
        return registration;
    }

}
