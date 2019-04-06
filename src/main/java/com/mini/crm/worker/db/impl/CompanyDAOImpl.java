package com.mini.crm.worker.db.impl;

import com.mini.crm.worker.config.DatabaseConfiguration;
import com.mini.crm.worker.db.VertexDAO;
import com.mini.crm.worker.model.ClassName;
import com.mini.crm.worker.model.Company;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CompanyDAOImpl implements VertexDAO<Company> {

    private static final String SQL_READ = "Select from Company where name = ?";
    private static final String SQL_READ_RID = "Select from Company where @rid = ?";
    private static final String SQL_READ_ALL = "Select from Company";
    private static final String SQL_UPDATE = "Update Company set name = ? where name = ?";
    private static final String SQL_DELETE = "Delete from Company where name = ? UNSAFE";

    private Company mainCompany = new Company();

    @Autowired
    DatabaseConfiguration db;

    @Override
    public Company create(Company node) {
        db.openSession();
        OVertex saveNode = db.getDbSession().newVertex(ClassName.V_COMPANY.toString());
        saveNode.setProperty("name", node.getName());
        saveNode.save();
        db.closeSession();
        return node;
    }

    @Override
    public Company read(Company node) {
        db.openSession();
        OResultSet resultSet = db.getDbSession().query(SQL_READ, node.getName());
        Company company = mainCompany.convert(resultSet.stream().findFirst());
        resultSet.close();
        db.closeSession();
        return company;
    }

    @Override
    public Company read(String rid) {
        db.openSession();
        OResultSet resultSet = db.getDbSession().query(SQL_READ_RID, rid);
        Company company = mainCompany.convert(resultSet.stream().findFirst());
        db.closeSession();
        return company;
    }

    @Override
    public Company update(Company newNode, Company oldNode) {
        db.openSession();
        OResultSet result = db.getDbSession().command(SQL_UPDATE, newNode.getName(), oldNode.getName());
        result.close();
        db.closeSession();
        return newNode;
    }

    @Override
    public void delete(Company node) {
        db.openSession();
        OResultSet result = db.getDbSession().command(SQL_DELETE, node.getName());
        result.close();
        db.closeSession();
    }

    @Override
    public List<Company> readAll() {
        List<Company> companyList = new ArrayList<>();
        db.openSession();
        OResultSet result = db.getDbSession().query(SQL_READ_ALL);
        result.stream().forEach(item -> companyList.add(mainCompany.convert(Optional.of(item))));
        db.closeSession();
        return companyList;
    }
}
