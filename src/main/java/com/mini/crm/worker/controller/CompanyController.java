package com.mini.crm.worker.controller;

import com.mini.crm.worker.controller.data.Response;
import com.mini.crm.worker.controller.data.ResponseStatus;
import com.mini.crm.worker.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/company", produces = "application/json; charset=UTF-8")
public class CompanyController {

    @Autowired
    CompanyServiceImpl companyService;

    @PostMapping
    public Response save(@RequestParam("name") String name) {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(companyService.save(name))
                .build();
    }

    @GetMapping
    public Response get(@RequestParam("name") String name) {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(companyService.get(name))
                .build();
    }

    @PutMapping
    public Response edit(@RequestParam("new_name") String newName,
                         @RequestParam("old_name") String oldName) {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(companyService.edit(newName, oldName))
                .build();
    }

    @DeleteMapping
    public Response remove(@RequestParam("name") String name) {
        companyService.remove(name);
        return Response.builder()
                .status(ResponseStatus.OK)
                .build();
    }

    @GetMapping(value = "/all")
    public Response getAll() {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(companyService.getAll())
                .build();
    }
}
