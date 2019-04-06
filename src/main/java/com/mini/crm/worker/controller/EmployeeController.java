package com.mini.crm.worker.controller;

import com.mini.crm.worker.controller.data.Response;
import com.mini.crm.worker.controller.data.ResponseStatus;
import com.mini.crm.worker.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/employee", produces = "application/json; charset=UTF-8")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @PostMapping
    public Response save(@RequestParam("first_name") String firstName,
                         @RequestParam("last_name") String lastName,
                         @RequestParam("email") String email,
                         @RequestParam("phone") String phone) {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(employeeService.save(firstName,lastName,email,phone))
                .build();
    }

    @GetMapping
    public Response get(@RequestParam("first_name") String firstName) {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(employeeService.get(firstName))
                .build();
    }

    @PutMapping
    public Response edit(@RequestParam("new_first_name") String newFirstName,
                         @RequestParam("new_last_name") String newLastName,
                         @RequestParam("new_email") String newEmail,
                         @RequestParam("new_phone") String newPhone,
                         @RequestParam("old_first_name") String oldFirstName) {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(employeeService.edit(newFirstName, newLastName, newEmail, newPhone, oldFirstName))
                .build();
    }

    @DeleteMapping
    public Response remove(@RequestParam("first_name") String firstName) {
        employeeService.remove(firstName);
        return Response.builder()
                .status(ResponseStatus.OK)
                .build();
    }

    @GetMapping(value = "/all")
    public Response getAll() {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(employeeService.getAll())
                .build();
    }
}
