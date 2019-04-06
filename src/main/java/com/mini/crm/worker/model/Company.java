package com.mini.crm.worker.model;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResult;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.List;
import java.util.Optional;

@Data
@ToString
@NoArgsConstructor
@FieldNameConstants
public class Company implements Converter<Company, Optional<OResult>> {
    private String name;
    private List<Employee> employees;

    @Override
    public Company convert(Optional<OResult> result) {
        Company company = new Company();
        if (result.isPresent()) {
            company.setName(result.get().getProperty("name"));
        }
        return company;
    }

    @Override
    public Company convert(String... strings) {
        Company company = new Company();
        company.setName(strings[0]);
        return company;
    }

    @Override
    public OVertex convertToVertex(Company company, ODatabaseSession session) {
        OVertex vertex = session.newVertex(ClassName.V_COMPANY.toString());
        vertex.setProperty("name", company.getName());
        return vertex;
    }
}
