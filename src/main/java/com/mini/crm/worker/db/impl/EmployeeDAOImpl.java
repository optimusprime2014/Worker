package com.mini.crm.worker.db.impl;

import com.mini.crm.worker.config.DatabaseConfiguration;
import com.mini.crm.worker.db.VertexDAO;
import com.mini.crm.worker.model.ClassName;
import com.mini.crm.worker.model.Employee;
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
public class EmployeeDAOImpl implements VertexDAO<Employee> {

    private static final String SQL_READ = "Select from Employee where firstName = ?";
    private static final String SQL_READ_RID = "Select from Employee where @rid = ?";
    private static final String SQL_READ_ALL = "Select from Employee";
    private static final String SQL_UPDATE = "Update Employee set firstName = ?, lastName = ?, email = ?, phone = ? where firstName = ?";
    private static final String SQL_DELETE = "Delete from Employee where firstName = ? UNSAFE";

    private Employee mainEmployee = new Employee();

    @Autowired
    DatabaseConfiguration db;

    @Override
    public Employee create(Employee node) {
        db.openSession();
        OVertex saveNode = db.getDbSession().newVertex(ClassName.V_EMPLOYEE.toString());
        saveNode.setProperty("firstName", node.getFirstName());
        saveNode.setProperty("lastName", node.getLastName());
        saveNode.setProperty("email", node.getEmail());
        saveNode.setProperty("phone", node.getPhone());
        saveNode.save();
        db.closeSession();
        return node;
    }

    @Override
    public Employee read(Employee node) {
        db.openSession();
        OResultSet resultSet = db.getDbSession().query(SQL_READ, node.getFirstName());
        Employee employee = mainEmployee.convert(resultSet.stream().findFirst());
        resultSet.close();
        db.closeSession();
        return employee;
    }

    @Override
    public Employee read(String rid) {
        db.openSession();
        OResultSet resultSet = db.getDbSession().query(SQL_READ_RID, rid);
        Employee employee = mainEmployee.convert(resultSet.stream().findFirst());
        resultSet.close();
        db.closeSession();
        return employee;
    }

    @Override
    public Employee update(Employee newNode, Employee oldNode) {
        db.openSession();
        OResultSet result = db.getDbSession().command(SQL_UPDATE,
                newNode.getFirstName(),
                newNode.getLastName(),
                newNode.getEmail(),
                newNode.getPhone(),
                oldNode.getFirstName());
        result.close();
        db.closeSession();
        return newNode;
    }

    @Override
    public void delete(Employee node) {
        db.openSession();
        OResultSet result = db.getDbSession().command(SQL_DELETE, node.getFirstName());
        result.close();
        db.closeSession();
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> employeeList = new ArrayList<>();
        db.openSession();
        OResultSet result = db.getDbSession().query(SQL_READ_ALL);
        result.stream().forEach(item -> employeeList.add(mainEmployee.convert(Optional.of(item))));
        db.closeSession();
        return employeeList;
    }
}
