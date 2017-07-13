package com.itacademy.config;


import com.itacademy.logs.AspectLogs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy

public class AspectConfig {

    @Bean
    public AspectLogs aspectLogs() {
        return new AspectLogs();
    }
}
