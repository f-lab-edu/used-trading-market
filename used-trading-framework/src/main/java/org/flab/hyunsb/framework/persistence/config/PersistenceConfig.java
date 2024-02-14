package org.flab.hyunsb.framework.persistence.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.flab.hyunsb.framework.persistence.repository")
@EntityScan(basePackages = "org.flab.hyunsb.framework.persistence.entity")
@ComponentScan(basePackages = "org.flab.hyunsb.framework.persistence")
public class PersistenceConfig {

}