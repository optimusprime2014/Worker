package com.mini.crm.worker.model;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResult;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.apache.logging.log4j.util.Strings;

import java.util.Optional;

@Data
@ToString
@NoArgsConstructor
@FieldNameConstants
public class Employee implements Converter<Employee, Optional<OResult>> {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @Override
    public Employee convert(Optional<OResult> result) {
        Employee employee = new Employee();
        if (result.isPresent()) {
            employee.setFirstName(result.get().getProperty("firstName"));
            employee.setLastName(result.get().getProperty("lastName"));
            employee.setEmail(result.get().getProperty("email"));
            employee.setPhone(result.get().getProperty("phone"));
        }
        return employee;
    }

    @Override
    public Employee convert(String... strings) {
        Employee employee = new Employee();
        employee.setFirstName(getValue(0, strings));
        employee.setLastName(getValue(1, strings));
        employee.setEmail(getValue(2, strings));
        employee.setPhone(getValue(3, strings));
        return employee;
    }

    private String getValue(int index, String[] strings) {
        if (strings.length > index && strings[index] != null) {
            return strings[index];
        }
        return Strings.EMPTY;
    }

    @Override
    public OVertex convertToVertex(Employee employee, ODatabaseSession session) {
        OVertex vertex = session.newVertex(ClassName.V_EMPLOYEE.toString());
        vertex.setProperty("firstName", employee.getFirstName());
        vertex.setProperty("lastName", employee.getLastName());
        vertex.setProperty("email", employee.getEmail());
        vertex.setProperty("phone", employee.getPhone());
        return vertex;
    }
}
