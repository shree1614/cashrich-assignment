package com.cashrich.coins.response;

import com.cashrich.coins.entity.User;

public class Response {
    private Boolean success;
    private User data;

    private String message;

    private String error;


    public Response(Boolean success, User data, String message, String error) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.error = error;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
