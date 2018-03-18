package com.common.configuration;

import com.core.exception.CustomSimpleMappingExceptionResolver;
import com.core.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalExceptionConfiguration {
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public CustomSimpleMappingExceptionResolver customSimpleMappingExceptionResolver() {
        return new CustomSimpleMappingExceptionResolver();
    }
}
