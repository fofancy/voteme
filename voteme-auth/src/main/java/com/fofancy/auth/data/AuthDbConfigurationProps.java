package com.fofancy.auth.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by User on 14.07.2017.
 */
@Component
@PropertySource("classpath:db/db.properties")
@ConfigurationProperties("db.auth")
public class AuthDbConfigurationProps {
    private Datasource datasource;
    private Hibernate hibernate;

    private String basePackage;

    public AuthDbConfigurationProps() {
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

    public Hibernate getHibernate() {
        return hibernate;
    }

    public void setHibernate(Hibernate hibernate) {
        this.hibernate = hibernate;
    }

    public static class Datasource {
        private String driver;
        private String url;
        private String username;
        private String password;

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {

            this.driver = driver;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class Hibernate {
        private String dialect;
        private String showSql;
        private String hbm2ddlAuto;

        public String getDialect() {
            return dialect;
        }

        public void setDialect(String dialect) {
            this.dialect = dialect;
        }

        public String getShowSql() {
            return showSql;
        }

        public void setShowSql(String showSql) {
            this.showSql = showSql;
        }

        public String getHbm2ddlAuto() {
            return hbm2ddlAuto;
        }

        public void setHbm2ddlAuto(String hbm2ddlAuto) {
            this.hbm2ddlAuto = hbm2ddlAuto;
        }
    }

}
