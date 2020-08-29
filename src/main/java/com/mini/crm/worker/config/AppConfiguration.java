package com.mini.crm.worker.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    private static final String FILE_NAME = "worker.png";

    @Value("${image.path}")
    private String path;

    public String getPath(){
        return StringUtils.join(path, FILE_NAME);
    }
}
