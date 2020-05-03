package com.kubar95.notix.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfiguration{

    @Bean
    public WebMvcConfigurer forwardToIndex() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/{spring:\\w+}")
                        .setViewName("forward:/");
                registry.addViewController("/**/{spring:\\w+}")
                        .setViewName("forward:/");
                registry.addViewController("/{spring:\\w+}/**{spring:?!(\\.js|\\.css)$}")
                        .setViewName("forward:/");
            }
        };
    }

}