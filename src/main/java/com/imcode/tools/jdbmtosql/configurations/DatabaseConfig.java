package com.imcode.tools.jdbmtosql.configurations;

import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.transfer.interfaces.DatabaseLoader;
import jdbm.btree.BTree;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import javax.annotation.Resource;
import java.nio.ByteBuffer;
import java.util.Properties;

@Configuration
@ComponentScan({"com.imcode.tools.jdbmtosql.transfer.services.dbload"})
@EnableTransactionManagement
@EnableJpaRepositories("com.imcode.tools.jdbmtosql.repositories")
public class DatabaseConfig {

    @Resource
    private Environment environment;

    private final DatabaseLoader databaseLoader;

    @Autowired
    public DatabaseConfig(DatabaseLoader databaseLoader) {
        this.databaseLoader = databaseLoader;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty("JdbcDriver"));
        dataSource.setUrl(environment.getRequiredProperty("JdbcUrl"));
        dataSource.setUsername(environment.getRequiredProperty("Username"));
        dataSource.setPassword(environment.getRequiredProperty("Password"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("com.imcode.tools.jdbmtosql.entities");

        entityManagerFactoryBean.setJpaProperties(hibProperties());

        return entityManagerFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        properties.put("hibernate.max_fetch_depth", 3);
        properties.put("hibernate.jdbc.fetch_size", 50);
        properties.put("hibernate.jdbc.batch_size", 10);
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.use_sql_comments", true);
        properties.put("hibernate.dialect", environment.getRequiredProperty("HibernateDialect"));
        properties.put("hibernate.pool_size", 10);
        properties.put("current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        properties.put("cache.provider_class", "org.hibernate.cache.internal.StandardQueryCache");
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("HibernateHbm2Ddl"));
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean("dataDb")
    public BTree loadDataDb() throws Exception {
        return databaseLoader.load(HdbmDatabasesDescription.DATA);
    }

    @Bean("eventsDb")
    public BTree loadEventsDb() throws Exception {
        return databaseLoader.load(HdbmDatabasesDescription.EVENTS);
    }

}
