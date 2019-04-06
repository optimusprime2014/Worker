package com.mini.crm.worker.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Data
@ToString
@NoArgsConstructor
@FieldNameConstants
public class Relation {
    private Company company;
    private Employee employee;
}
