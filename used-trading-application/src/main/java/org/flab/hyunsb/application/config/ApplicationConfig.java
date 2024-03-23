package org.flab.hyunsb.application.config;

import org.flab.hyunsb.application.util.ActorTokenDateGenerator;
import org.flab.hyunsb.application.util.DateGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
    "org.flab.hyunsb.application.service",
    "org.flab.hyunsb.application.config"}, lazyInit = true)
public class ApplicationConfig {

    @Bean
    public DateGenerator dateGenerator() {
        return new ActorTokenDateGenerator();
    }
}
