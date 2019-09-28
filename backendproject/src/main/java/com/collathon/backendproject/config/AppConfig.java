package com.collathon.backendproject.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.collathon.backendproject.sequence",
        "com.collathon.backendproject.model",
})
public class AppConfig {
}
