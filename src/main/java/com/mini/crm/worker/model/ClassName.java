package com.mini.crm.worker.model;

public enum ClassName {
    V_COMPANY("Company"),
    V_EMPLOYEE("Employee"),
    E_RELATION("Relation"),
    ;

    private final String name;

    /**
     * @param name
     */
    ClassName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
