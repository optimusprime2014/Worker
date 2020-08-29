package com.mini.crm.worker.config;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.ArangoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableArangoRepositories(basePackages = {"com.mini.crm.worker"})
public class DatabaseConfiguration implements ArangoConfiguration {
    public static final String COMPANY = "Company";
    public static final String EMPLOYEE = "Employee";
    public static final String EMPLOYEE_OF = "employeeOf";

    @Value("${arangodb.host}")
    private String host;

    @Value("${arangodb.port}")
    private String port;

    @Value("${arangodb.user}")
    private String user;

    @Value("${arangodb.password}")
    private String pwd;

    @Value("${arangodb.database}")
    private String dbName;

    @Override
    public ArangoDB.Builder arango() {
        return new ArangoDB.Builder()
                .host(host, Integer.parseInt(port))
                .user(user)
                .password(pwd);
    }

    @Override
    public String database() {
        return dbName;
    }
}
