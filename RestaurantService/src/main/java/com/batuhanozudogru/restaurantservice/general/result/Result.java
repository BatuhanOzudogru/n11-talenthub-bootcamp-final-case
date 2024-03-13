package com.batuhanozudogru.restaurantservice.general.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Result {
    private boolean status;
    private String code;
    private String message;
}

