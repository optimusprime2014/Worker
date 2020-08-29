package com.mini.crm.worker.repo;

import com.arangodb.springframework.repository.ArangoRepository;
import com.mini.crm.worker.model.Company;

public interface CompanyRepo extends ArangoRepository<Company, String> {
    Company findByName(String name);
}
