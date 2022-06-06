package com.atlas.skull.Config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableJpaRepositories(entityManagerFactoryRef = "baseSkullEntityManagerFactory",
        basePackages = {"com.atlas.skull.BaseSkullRepository"}, transactionManagerRef = "baseSkullTransactionManager")
@EnableTransactionManagement
@Configuration

public class BaseSchoolDSConfig {

    @Bean(name = "baseSkullDS")
    @ConfigurationProperties(prefix = "spring.baseskull.datasource")
    public DataSource dataSource(){

        return DataSourceBuilder.create().build();
    }

    @Bean(name = "baseSkullEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
                                                               @Qualifier("baseSkullDS")DataSource dataSource){
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return builder.dataSource(dataSource).properties(properties).packages("com.atlas.skull.BaseSkullModel")
                .persistenceUnit("*").build();
    }

    @Bean(name = "baseSkullTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("baseSkullEntityManagerFactory")EntityManagerFactory
                                                         entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
