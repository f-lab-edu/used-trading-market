package org.flab.hyunsb.bootstrap.config;

import org.flab.hyunsb.application.config.ApplicationConfig;
import org.flab.hyunsb.framework.persistence.config.PersistenceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "org.flab.hyunsb.bootstrap.rest")
@Import(value = {ApplicationConfig.class, PersistenceConfig.class})
public class BootstrapConfig {

}
