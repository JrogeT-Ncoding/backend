package com.jroget.ncodingbackend.models;

public class Response {
    private String message;
    private String status;
    private Object data;

    public Response(String status, String message, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
