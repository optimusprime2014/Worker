package com.mini.crm.worker.controller.data;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class Response<R> {
    private ResponseStatus status;
    private String message;
    private R data;
}
