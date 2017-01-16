package com.imcode.tools.jdbmtosql.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({DatabaseConfig.class})
@PropertySource("classpath:application.properties")
public class MainConfig {


}
