package main.java.ru.mamishev.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


/**
 * Created by mamishevda on 25.07.2016.
 */
public class Liq {

    public static void main(String[] args) throws ClassNotFoundException {
        SpringLiquibase springLiquibase = new Liq().liquibase();
    }

    public DataSource dataSource() throws ClassNotFoundException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(String.valueOf(Class.forName("org.h2.Driver")));
        dataSource.setUrl("jdbc:h2:mem:default");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    public SpringLiquibase liquibase() throws ClassNotFoundException {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("main/resources/DBLogFile");
        liquibase.getChangeLog();
        return liquibase;
    }
}