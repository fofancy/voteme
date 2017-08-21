package com.fofancy.auth.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by User on 15.07.2017.
 */
@Configuration
@ComponentScan("com.fofancy")
//@Profile("dev")
public class DatabaseConfig {
    private AuthDbConfigurationProps properties;

    public AuthDbConfigurationProps getProperties() {
        return properties;
    }

    @Autowired
    public void setProperties(AuthDbConfigurationProps properties) {
        this.properties = properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        System.out.println("Driver 1324253216: ");
        System.out.println("Driver 1324253216: " + properties.getDatasource().getDriver());

        dataSource.setDriverClassName(properties.getDatasource().getDriver());
        dataSource.setUrl(properties.getDatasource().getUrl());
        dataSource.setUsername(properties.getDatasource().getUsername());
        dataSource.setPassword(properties.getDatasource().getPassword());

        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);

        return hibernateJpaVendorAdapter;
    }

    @Bean
    public Properties jpaAdditionalProperties() {
        Properties jpaAdditionalProperties = new Properties();

        jpaAdditionalProperties.put("hibernate.dialect", properties.getHibernate().getDialect());
        jpaAdditionalProperties.put("hibernate.hbm2dll.auto", properties.getHibernate().getHbm2ddlAuto());

        return jpaAdditionalProperties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactory.setPackagesToScan(properties.getBasePackage());
        entityManagerFactory.setJpaProperties(jpaAdditionalProperties());

        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager tx = new JpaTransactionManager();
        tx.setEntityManagerFactory(emf);

        return tx;
    }
}
