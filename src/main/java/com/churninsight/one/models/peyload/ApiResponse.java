package com.churninsight.one.models.peyload;

import java.util.Date;

public class ApiResponse {

    private Date time = new Date();
    private String message;
    private Boolean success;
    private Object data;

    public ApiResponse() {
    }

    public ApiResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public ApiResponse(String message, Boolean success, Object data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public ApiResponse(Object data, String message, Boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setsuccess(Boolean success) {
        this.success = success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
