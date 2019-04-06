package com.mini.crm.worker.service.impl;

import com.mini.crm.worker.db.impl.CompanyDAOImpl;
import com.mini.crm.worker.model.Company;
import com.mini.crm.worker.service.VertexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CompanyServiceImpl implements VertexService<Company, String> {

    private Company mainCompany = new Company();

    @Autowired
    CompanyDAOImpl companyDAO;

    @Override
    public Company save(String... fields) {
        return companyDAO.create(mainCompany.convert(fields));
    }

    @Override
    public Company get(String... fields) {
        return companyDAO.read(mainCompany.convert(fields));
    }

    @Override
    public Company edit(String... fields) {
        Company newCompany = mainCompany.convert(fields[0]);
        Company oldCompany = mainCompany.convert(fields[1]);
        return companyDAO.update(newCompany, oldCompany);
    }

    @Override
    public void remove(String... fields) {
        companyDAO.delete(mainCompany.convert(fields));
    }

    @Override
    public List<Company> getAll() {
        return companyDAO.readAll();
    }
}
