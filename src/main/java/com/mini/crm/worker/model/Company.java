package com.mini.crm.worker.model;

import com.arangodb.springframework.annotation.Document;
import com.mini.crm.worker.config.DatabaseConfiguration;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Document(DatabaseConfiguration.COMPANY)
public class Company{
    @Id
    private String id;
    private String name;

    public Company(String name) {
        super();
        this.name = name;
    }
}
