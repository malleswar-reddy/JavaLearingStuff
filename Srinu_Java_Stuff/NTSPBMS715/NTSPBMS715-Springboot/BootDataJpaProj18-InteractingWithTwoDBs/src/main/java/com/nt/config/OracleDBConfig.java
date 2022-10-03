package com.nt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@EnableTransactionManagement
@Configuration
@PropertySource({ "classpath:application.properties" })
@EntityScan("com.nt.model.product")
@EnableJpaRepositories(basePackages = "com.nt.repo.product",
        entityManagerFactoryRef = "oraEMF",
        transactionManagerRef = "oraTxMgmr")
public class OracleDBConfig {

    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix = "oracle.datasource")
    @Primary
    public DataSource createOraDs() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("oracle.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("oracle.datasource.jdbc-url"));
        dataSource.setUsername(env.getProperty("oracle.datasource.username"));
        dataSource.setPassword(env.getProperty("oracle.datasource.password"));
       return dataSource;

      /*  return DataSourceBuilder.create()
                .driverClassName(env.getProperty("oracle.datasource.driver-class-name"))
                .url(env.getProperty("oracle.datasource.jdbc-url"))
                .username(env.getProperty("oracle.datasource.username"))
                .password(env.getProperty("oracle.datasource.password"))
                .build();*/
    }

    @Bean(name = "oraEMF")
    @Primary
    public LocalContainerEntityManagerFactoryBean createOraEntityFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(createOraDs());
        em.setPackagesToScan("com.nt.model.product");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        em.setJpaPropertyMap(properties);

        return em;


       /* //create Properties object having hibernate properties
        Map< String, String > props = new HashMap();
//        props.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.hbm2ddl.auto", "update");
        //create and return LocalContainerEntityManagerFactoryBean
        return builder.dataSource(createOraDs()).packages("com.nt.model.product").properties(props).build();*/
    }

    @Bean(name = "oraTxMgmr")
    @Primary
    public JpaTransactionManager createOraTxMgmr(EntityManagerFactory emFactory) {
        return new JpaTransactionManager(emFactory);
    }

}
