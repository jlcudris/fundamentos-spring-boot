package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperites;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperitesImplement;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

//anotacion para aplication properties
@Configuration
//anotacion para coneection properties seria otra manera de obtenr propiedades o los valores de variables de entorno
//PropertySource al usar esta anotacion podemos pasarle el path del archivo propertie que usaremos
@PropertySource("classpath:connection.properties")
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {

    @Value("${value.name}")
    private String name;

    @Value("${value.apellido}")
    private String apellido;

    @Value("${jdbc.url}")
    private String jdbcurl;
    @Value("${driver}")
    private String driver;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;

    @Bean
    public MyBeanWithProperites function(){

        return new  MyBeanWithProperitesImplement(name,apellido);
    }
//configuracion del datasource a nivel d clases
    @Bean
    public DataSource dataSource (){

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(jdbcurl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);


        return dataSourceBuilder.build();

    }


}
