package com.imcode.tools.jdbmtosql.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class MainConfig {

    private final Environment environment;

    @Autowired
    public MainConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertyPlaceholderConfigurer();
    }

    @Bean
    public DatabaseConfig databaseConfig() {
        return new DatabaseConfig(environment);
    }

    @Bean
    public ApplicationProperties applicationProperties() {
        return new ApplicationProperties(environment);
    }

}
