package com.mini.crm.worker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class WorkerConfiguration {
    private static String DB_NAME;
    private static String DB_USER;
    private static String DB_PASS;

    @Value("${spring.datasource.name}")
    private void setDbName(String db) {
        DB_NAME = db;
    }

    public String getDbName() {
        return DB_NAME;
    }

    @Value("${spring.datasource.username}")
    private void setDbUser(String user) {
        DB_USER = user;
    }

    public String getDbUser() {
        return DB_USER;
    }

    @Value("${spring.datasource.password}")
    private void setDbPass(String password) {
        DB_PASS = password;
    }

    public String getDbPass() {
        return DB_PASS;
    }
}
