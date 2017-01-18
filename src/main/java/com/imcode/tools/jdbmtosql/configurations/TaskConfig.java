package com.imcode.tools.jdbmtosql.configurations;

import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
@EnableAsync
@Import({DatabaseConfig.class})
@ComponentScan({"com.imcode.tools.jdbmtosql.transfer.services.entitymappers",
        "com.imcode.tools.jdbmtosql.transfer.services.schedulehelpers",
        "com.imcode.tools.jdbmtosql.transfer.services.schedulers"})
@PropertySource("classpath:application.properties")
public class TaskConfig implements SchedulingConfigurer {

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertyPlaceholderConfigurer();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(HdbmDatabasesDescription.values().length);
    }
}
