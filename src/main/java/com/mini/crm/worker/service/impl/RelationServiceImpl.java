package com.mini.crm.worker.service.impl;

import com.mini.crm.worker.db.impl.CompanyDAOImpl;
import com.mini.crm.worker.db.impl.EmployeeDAOImpl;
import com.mini.crm.worker.db.impl.RelationDAOImpl;
import com.mini.crm.worker.model.Company;
import com.mini.crm.worker.model.Employee;
import com.mini.crm.worker.model.Relation;
import com.mini.crm.worker.service.EdgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RelationServiceImpl implements EdgeService<Relation, String> {

    private Company mainCompany = new Company();
    private Employee mainEmployee = new Employee();

    @Autowired
    CompanyDAOImpl companyDAO;

    @Autowired
    EmployeeDAOImpl employeeDAO;

    @Autowired
    RelationDAOImpl relationDAO;

    @Override
    public Relation save(String... strings) {
        Company nodeIn = companyDAO.read(mainCompany.convert(strings[0]));
        Employee nodeOut = employeeDAO.read(mainEmployee.convert(strings[1]));
        return relationDAO.create(nodeIn, nodeOut);
    }

    @Override
    public Relation get(String... strings) {
        Company nodeIn = companyDAO.read(mainCompany.convert(strings[0]));
        Employee nodeOut = employeeDAO.read(mainEmployee.convert(strings[1]));
        return relationDAO.read(nodeIn, nodeOut);
    }

    @Override
    public Relation edit(String... strings) {
        //TODO need impl
        return null;
    }

    @Override
    public void remove(String... strings) {
        Company nodeIn = companyDAO.read(mainCompany.convert(strings[0]));
        Employee nodeOut = employeeDAO.read(mainEmployee.convert(strings[1]));
        relationDAO.delete(nodeIn, nodeOut);
    }

    @Override
    public List<Relation> getAll(String... strings) {
        Company nodeIn = companyDAO.read(mainCompany.convert(strings));
        return relationDAO.readAll(nodeIn);
    }
}
