package com.batuhanozudogru.userservice.general.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResultData<T> extends Result{
    private T data;

    @JsonCreator
    public ResultData(@JsonProperty("status") boolean status,
                      @JsonProperty("code") String code,
                      @JsonProperty("message") String message,
                      @JsonProperty("data") T data
                      ) {
        super(status, code, message, LocalDateTime.now());
        this.data = data;
    }
}
