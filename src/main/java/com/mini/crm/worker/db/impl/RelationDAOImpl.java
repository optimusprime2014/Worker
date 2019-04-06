package com.mini.crm.worker.db.impl;

import com.mini.crm.worker.db.EdgeDAO;
import com.mini.crm.worker.config.DatabaseConfiguration;
import com.mini.crm.worker.model.*;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.record.OEdge;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RelationDAOImpl implements EdgeDAO<Relation, Company, Employee>, Converter<Relation, Optional<OResult>> {

    private static final String SQL_READ = "Select from Relation " +
            "where in in (Select @rid from Company where name = ? ) " +
            "and out in (Select @rid from Employee where firstName = ?)";
    private static final String SQL_READ_RID = "";
    private static final String SQL_READ_ALL = "Select from Relation";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "Delete from Relation " +
            "where in in (Select @rid from Company where name = ? ) " +
            "and out in (Select @rid from Employee where firstName = ?) UNSAFE";

    private Company mainCompany = new Company();
    private Employee mainEmployee = new Employee();

    @Autowired
    CompanyDAOImpl companyDAO;

    @Autowired
    EmployeeDAOImpl employeeDAO;

    @Autowired
    DatabaseConfiguration db;

    @Override
    public Relation create(Company nodeIn, Employee nodeOut) {
        db.openSession();
        OVertex in = mainCompany.convertToVertex(nodeIn, db.getDbSession());
        OVertex out = mainEmployee.convertToVertex(nodeOut, db.getDbSession());
        OEdge saveEdge = db.getDbSession().newEdge(in, out);
        saveEdge.save();
        db.closeSession();
        return null;
    }

    @Override
    public Relation read(Company nodeIn, Employee nodeOut) {
        db.openSession();
        OResultSet resultSet = db.getDbSession().query(SQL_READ, nodeIn.getName(), nodeOut.getFirstName());
        Relation relation = this.convert(resultSet.stream().findFirst());
        resultSet.close();
        db.closeSession();
        return relation;
    }

    @Override
    public Relation update(Company nodeIn, Employee nodeOut) {
        //TODO need impl
        return null;
    }

    @Override
    public void delete(Company nodeIn, Employee nodeOut) {
        db.openSession();
        OResultSet result = db.getDbSession().command(SQL_DELETE, nodeIn.getName(), nodeOut.getFirstName());
        result.close();
        db.closeSession();
    }

    @Override
    public List<Relation> readAll(Company nodeIn) {
        List<Relation> relationList = new ArrayList<>();
        db.openSession();
        OResultSet result = db.getDbSession().query(SQL_READ_ALL);
        result.stream()
                .filter(item -> this.convert(Optional.of(item)).getCompany().getName().equals(nodeIn.getName()))
                .forEach(item -> relationList.add(this.convert(Optional.of(item))));
        db.closeSession();
        return relationList;
    }

    @Override
    public Relation convert(Optional<OResult> result) {
        Relation relation = new Relation();
        if (result.isPresent()) {
            relation.setCompany(companyDAO.read(result.get().getProperty("in").toString()));
            relation.setEmployee(employeeDAO.read(result.get().getProperty("out").toString()));
        }
        return relation;
    }

    @Override
    public Relation convert(String... strings) {
        return null;
    }

    @Override
    public OVertex convertToVertex(Relation relation, ODatabaseSession session) {
        return null;
    }
}
