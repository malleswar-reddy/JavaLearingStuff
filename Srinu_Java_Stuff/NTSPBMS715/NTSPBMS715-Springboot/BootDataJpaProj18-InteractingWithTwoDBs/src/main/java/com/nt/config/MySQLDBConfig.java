//MySQLDBConfig.java
package com.nt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.nt.repo.offers",
        entityManagerFactoryRef = "mySQLEMF",
        transactionManagerRef = "mySQLTxMgmr")
@PropertySource({ "classpath:application.properties" })
@EntityScan("com.nt.model.offers")
public class MySQLDBConfig {

    @Autowired
    private Environment env;


    @Bean
    @ConfigurationProperties(prefix = "mysql.datasource")
    public DataSource createMySQLDS() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("mysql.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("mysql.datasource.jdbc-url"));
        dataSource.setUsername(env.getProperty("mysql.datasource.username"));
        dataSource.setPassword(env.getProperty("mysql.datasource.password"));
        return dataSource;
        /*
        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("mysql.datasource.driver-class-name"))
                .url(env.getProperty("mysql.datasource.jdbc-url"))
                .username(env.getProperty("mysql.datasource.username"))
                .password(env.getProperty("mysql.datasource.password"))
                .build();*/
    }

    @Bean(name = "mySQLEMF")
    public LocalContainerEntityManagerFactoryBean createEMFactory() {
        //create Properties object having hibernate properties
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(createMySQLDS());
        em.setPackagesToScan("com.nt.model.offers");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map< String, String > props = new HashMap();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(props);

        return em;
        /*
        return builder.dataSource(createMySQLDS())
                .properties(props)
                .packages("com.nt.model.offers").build();*/
    }

    @Bean(name = "mySQLTxMgmr")
    public JpaTransactionManager createTxMgmr(@Qualifier("mySQLEMF")
                                                  EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

}
