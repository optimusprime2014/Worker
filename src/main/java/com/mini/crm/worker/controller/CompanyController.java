package com.mini.crm.worker.controller;

import com.mini.crm.worker.controller.data.Response;
import com.mini.crm.worker.controller.data.ResponseStatus;
import com.mini.crm.worker.model.Company;
import com.mini.crm.worker.repo.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/api/company", produces = "application/json; charset=UTF-8")
public class CompanyController {

    @Autowired
    private CompanyRepo companyRepo;

    @PostMapping
    public Response create(@RequestParam(value = "name") String name) {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(companyRepo.save(new Company(name)))
                .message("Company was saved")
                .build();
    }

    @GetMapping
    public Response get(@RequestParam("name") String name) {
        var company = companyRepo.findByName(name);
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(company)
                .message((company == null) ? "Couldn't find company: " + name : "")
                .build();
    }

    @PutMapping
    public Response update(@RequestParam("new_name") String newName,
                           @RequestParam("old_name") String oldName) {
        Response<Company> resp = Response.<Company>builder()
                .status(ResponseStatus.OK)
                .message("Couldn't find company: " + oldName)
                .build();

        var company = companyRepo.findByName(oldName);
        if (company != null) {
            company.setName(newName);
            companyRepo.save(company);
            resp.setData(company);
            resp.setMessage("Company was updated");
        }
        return resp;
    }

    @DeleteMapping
    public Response delete(@RequestParam("name") String name) {
        Response<Company> resp = Response.<Company>builder()
                .status(ResponseStatus.OK)
                .message("Couldn't find company: " + name)
                .build();

        var company = companyRepo.findByName(name);
        if (company != null) {
            companyRepo.delete(company);
            resp.setMessage("Company was deleted");
        }
        return resp;
    }

    @GetMapping(value = "/all")
    public Response getAll() {
        return Response.builder()
                .status(ResponseStatus.OK)
                .data(Arrays.asList(companyRepo.findAll()))
                .build();
    }
}
