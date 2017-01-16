package com.imcode.tools.jdbmtosql.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationProperties {

    private final Environment environment;

    @Autowired
    public ApplicationProperties(Environment environment) {
        this.environment = environment;
    }

}
