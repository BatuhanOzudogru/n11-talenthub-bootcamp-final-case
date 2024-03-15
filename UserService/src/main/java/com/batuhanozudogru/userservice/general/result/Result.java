package com.batuhanozudogru.userservice.general.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
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

}

