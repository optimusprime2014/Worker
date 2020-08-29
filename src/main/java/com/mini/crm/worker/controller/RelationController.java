package com.mini.crm.worker.controller;

import com.mini.crm.worker.config.AppConfiguration;
import com.mini.crm.worker.controller.data.Response;
import com.mini.crm.worker.controller.data.ResponseStatus;
import com.mini.crm.worker.model.EmployeeOf;
import com.mini.crm.worker.repo.CompanyRepo;
import com.mini.crm.worker.repo.EmployeeOfRepo;
import com.mini.crm.worker.repo.EmployeeRepo;
import com.mini.crm.worker.service.DataConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "/api/relation", produces = "application/json; charset=UTF-8")
public class RelationController {

    @Autowired
    private DataConvertService dataConvertService;
    @Autowired
    private AppConfiguration appConfiguration;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private EmployeeOfRepo employeeOfRepo;

    @PostMapping
    public Response create(@RequestParam("company_name") String companyName,
                           @RequestParam("employee_name") String employeeName) {
        var company = companyRepo.findByName(companyName);
        var employee = employeeRepo.findByName(employeeName);
        if (company == null || employee == null) {
            return Response.builder()
                    .status(ResponseStatus.OK)
                    .message("Wrong company/employee")
                    .build();
        }

        return Response.builder()
                .status(ResponseStatus.OK)
                .data(employeeOfRepo.save(new EmployeeOf(employee, company)))
                .build();
    }

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> get(@RequestParam(value = "company_name") String companyName) throws IOException {

        dataConvertService.executeGraphByCompany(companyName);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(Files.readAllBytes(Paths.get(appConfiguration.getPath())));
    }
}
