package com.mini.crm.worker.controller;

import com.mini.crm.worker.controller.data.Response;
import com.mini.crm.worker.controller.data.ResponseStatus;
import com.mini.crm.worker.model.Employee;
import com.mini.crm.worker.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/api/employee", produces = "application/json; charset=UTF-8")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @PostMapping
    public Response create(@RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("phone") String phone) {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(employeeRepo.save(new Employee(name, email, phone)))
                .message("Employee was saved")
                .build();
    }

    @GetMapping
    public Response get(@RequestParam("name") String name) {
        var employee = employeeRepo.findByName(name);
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(employee)
                .data((employee == null) ? "Couldn't find employee: " + name : "")
                .build();
    }

    @PutMapping
    public Response update(@RequestParam("new_name") String newName,
                         @RequestParam("old_name") String oldName) {
        Response<Employee> resp = Response.<Employee>builder()
                .status(ResponseStatus.OK)
                .message("Couldn't find employee: " + oldName)
                .build();
        var employee = employeeRepo.findByName(oldName);
        if (employee != null) {
            employee.setName(newName);
            employeeRepo.save(employee);
            resp.setData(employee);
            resp.setMessage("Employee was updated");
        }
        return resp;
    }

    @DeleteMapping
    public Response delete(@RequestParam("name") String name) {
        Response<Employee> resp = Response.<Employee>builder()
                .status(ResponseStatus.OK)
                .message("Couldn't find employee: " + name)
                .build();

        var employee = employeeRepo.findByName(name);
        if (employee != null) {
            employeeRepo.delete(employee);
            resp.setMessage("Employee was deleted");
        }
        return resp;
    }

    @GetMapping(value = "/all")
    public Response getAll() {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(Arrays.asList(employeeRepo.findAll()))
                .build();
    }
}
