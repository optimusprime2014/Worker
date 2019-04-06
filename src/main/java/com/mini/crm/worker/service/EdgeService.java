package com.mini.crm.worker.service;

import java.util.List;

public interface EdgeService<V, S> {

    V save(S... s);

    V get(S... s);

    V edit(S... s);

    void remove(S... s);

    List<V> getAll(S... s);
}
