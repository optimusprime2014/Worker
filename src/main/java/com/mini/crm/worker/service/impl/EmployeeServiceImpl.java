package com.mini.crm.worker.service.impl;

import com.mini.crm.worker.db.impl.EmployeeDAOImpl;
import com.mini.crm.worker.model.Employee;
import com.mini.crm.worker.service.VertexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements VertexService<Employee, String> {

    private Employee mainEmployee = new Employee();
    @Autowired
    EmployeeDAOImpl employeeDAO;

    @Override
    public Employee save(String... fields) {
        return employeeDAO.create(mainEmployee.convert(fields));
    }

    @Override
    public Employee get(String... fields) {
        return employeeDAO.read(mainEmployee.convert(fields));
    }

    @Override
    public Employee edit(String... fields) {
        Employee newEmployee = mainEmployee.convert(
                fields[0], fields[1], fields[2], fields[3]);
        Employee oldEmployee = mainEmployee.convert(fields[4]);
        return employeeDAO.update(newEmployee, oldEmployee);
    }

    @Override
    public void remove(String... fields) {
        employeeDAO.delete(mainEmployee.convert(fields));
    }

    @Override
    public List<Employee> getAll() {
        return employeeDAO.readAll();
    }
}
