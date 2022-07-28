package com.example.coffeshop.model.dto;

public class DefaultResponse<T> {
    private Boolean status;
    private String message;
    private T Data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() { return Data; }

    public void setData(T data) { Data = data; }
}
