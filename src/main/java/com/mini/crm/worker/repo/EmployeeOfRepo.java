package com.mini.crm.worker.repo;

import com.arangodb.springframework.repository.ArangoRepository;
import com.mini.crm.worker.model.Company;
import com.mini.crm.worker.model.EmployeeOf;

public interface EmployeeOfRepo extends ArangoRepository<EmployeeOf, String> {
    Iterable<EmployeeOf> findByCompany(Company company);
}
