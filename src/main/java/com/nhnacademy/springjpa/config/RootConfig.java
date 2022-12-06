package com.nhnacademy.springjpa.config;

import com.nhnacademy.springjpa.Base;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = { @ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public DataSource dataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        basicDataSource.setUrl("jdbc:mysql://133.186.151.141:3306/nhn_academy_39");
        basicDataSource.setUsername("nhn_academy_39");
        basicDataSource.setPassword("*6]fBj1*QmPlpH2c");
        basicDataSource.setInitialSize(2);
        basicDataSource.setMaxTotal(2);
        return basicDataSource;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("message");

        return messageSource;
    }
}
