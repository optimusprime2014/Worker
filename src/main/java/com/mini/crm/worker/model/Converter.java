package com.mini.crm.worker.model;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.record.OVertex;

public interface Converter<C, R> {
    C convert(R r);
    C convert(String... strings);
    OVertex convertToVertex (C c, ODatabaseSession session);
}
