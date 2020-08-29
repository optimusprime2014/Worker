package com.mini.crm.worker.model;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.mini.crm.worker.config.DatabaseConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Edge(DatabaseConfiguration.EMPLOYEE_OF)
public class EmployeeOf {
    @From
    private Employee employee;
    @To
    private Company company;
}
