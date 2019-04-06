package com.mini.crm.worker.controller;

import com.mini.crm.worker.controller.data.Response;
import com.mini.crm.worker.controller.data.ResponseStatus;
import com.mini.crm.worker.service.impl.RelationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/relation", produces = "application/json; charset=UTF-8")
public class RelationController {

    @Autowired
    RelationServiceImpl relationService;

    @PostMapping
    public Response save(@RequestParam("company_name") String companyName,
                         @RequestParam("employee_first_name") String employeeFirstName) {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(relationService.save(companyName, employeeFirstName))
                .build();
    }

    @GetMapping
    public Response get(@RequestParam("company_name") String companyName,
                        @RequestParam("employee_first_name") String employeeFirstName) {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(relationService.get(companyName, employeeFirstName))
                .build();
    }

    @DeleteMapping
    public Response remove(@RequestParam("company_name") String companyName,
                           @RequestParam("employee_first_name") String employeeFirstName) {
        relationService.remove(companyName, employeeFirstName);
        return Response.builder()
                .status(ResponseStatus.OK)
                .build();
    }

    @GetMapping(value = "/all")
    public Response getAll(@RequestParam("company_name") String companyName) {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(relationService.getAll(companyName))
                .build();
    }
}
