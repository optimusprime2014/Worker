package com.mini.crm.worker.db;

import java.util.List;

public interface EdgeDAO<R, C, E> {
    R create(C nodeIn, E nodeOut);

    R read(C nodeIn, E nodeOut);

    R update(C nodeIn, E nodeOut);

    void delete(C nodeIn, E nodeOut);

    List<R> readAll(C nodeIn);
}
