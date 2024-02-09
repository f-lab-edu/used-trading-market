package org.flab.hyunsb.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.flab.hyunsb.application.service"}, lazyInit = true)
public class ApplicationConfig {

}
