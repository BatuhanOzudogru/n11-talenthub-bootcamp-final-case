package com.batuhanozudogru.restaurantservice.general.result;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class Result {
    private boolean status;
    private String code;
    private String message;
    private LocalDateTime responseDate;

    public Result(boolean status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.responseDate = LocalDateTime.now();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(LocalDateTime responseDate) {
        this.responseDate = responseDate;
    }
}

