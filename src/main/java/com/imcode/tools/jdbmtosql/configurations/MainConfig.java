package com.imcode.tools.jdbmtosql.configurations;

import com.imcode.tools.jdbmtosql.tranfer.interfaces.DataReceiver;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;

@Configuration
@Import({DatabaseConfig.class})
@ComponentScan({"com.imcode.tools.jdbmtosql.tranfer.services"})
@PropertySource("classpath:application.properties")
public class MainConfig {

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertyPlaceholderConfigurer();
    }

}
