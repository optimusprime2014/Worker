package com.mini.crm.worker.repo;

import com.arangodb.springframework.repository.ArangoRepository;
import com.mini.crm.worker.model.Employee;

public interface EmployeeRepo extends ArangoRepository<Employee, String> {
    Employee findByName(String name);
}
