package com.imcode.tools.jdbmtosql.configurations;

import com.imcode.tools.jdbmtosql.enums.DatabaseDescription;
import com.imcode.tools.jdbmtosql.tranfer.interfaces.DatabaseLoader;
import jdbm.btree.BTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;

import java.io.IOException;

@Configuration
@Import({DatabaseConfig.class})
@ComponentScan({"com.imcode.tools.jdbmtosql.tranfer.services"})
@PropertySource("classpath:application.properties")
public class MainConfig {

    final DatabaseLoader databaseLoader;

    @Autowired
    public MainConfig(DatabaseLoader databaseLoader) {
        this.databaseLoader = databaseLoader;
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertyPlaceholderConfigurer();
    }

    @Bean("dataDb")
    public BTree loadDataDb() throws IOException {
        return databaseLoader.load(DatabaseDescription.DATA);
    }

    @Bean("eventsDb")
    public BTree loadEventsDb() throws IOException {
        return databaseLoader.load(DatabaseDescription.EVENTS);
    }

}
