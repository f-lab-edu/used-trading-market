package org.flab.hyunsb.bootstrap;

import org.flab.hyunsb.application.config.ApplicationConfig;
import org.flab.hyunsb.framework.persistence.config.PersistenceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {ApplicationConfig.class, PersistenceConfig.class})
public class UsedTradingApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsedTradingApplication.class, args);
    }
}
