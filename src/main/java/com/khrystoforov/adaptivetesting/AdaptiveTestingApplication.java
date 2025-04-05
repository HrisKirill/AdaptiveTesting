package com.khrystoforov.adaptivetesting;

import com.khrystoforov.adaptivetesting.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(ApplicationProperties.class)
public class AdaptiveTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdaptiveTestingApplication.class, args);
    }

}
