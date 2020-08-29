package com.mini.crm.worker.model;

import com.arangodb.springframework.annotation.Document;
import com.mini.crm.worker.config.DatabaseConfiguration;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Document(DatabaseConfiguration.EMPLOYEE)
public class Employee {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;

    public Employee(String name,String email, String phone) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
