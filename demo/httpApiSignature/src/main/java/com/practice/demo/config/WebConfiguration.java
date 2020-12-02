package com.practice.demo.config;

import com.practice.demo.filter.SignatureFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebConfiguration {

    @Autowired
    private SignatureFilter signatureFilter;

    @Bean
    public FilterRegistrationBean<Filter> signature() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.addUrlPatterns();
        registration.setFilter(signatureFilter);
        return registration;
    }


}
