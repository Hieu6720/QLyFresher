package com.management.fresher.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultResponse {
    private final Integer status;
    private final String message;
    private final Object result;

    public ResultResponse(Integer status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public ResultResponse(Integer status, String message){
        this.status = status;
        this.message = message;
        this.result = null;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getResult() {
        return result;
    }
}
