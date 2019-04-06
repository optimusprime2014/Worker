package com.mini.crm.worker.db;

import java.util.List;

public interface VertexDAO<V> {
    V create(V node);

    V read(String rid);

    V read(V node);

    V update(V newNode, V oldNode);

    void delete(V node);

    List<V> readAll();
}
