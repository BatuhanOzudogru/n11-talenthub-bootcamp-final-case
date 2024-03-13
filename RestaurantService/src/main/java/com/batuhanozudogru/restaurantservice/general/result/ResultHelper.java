package com.batuhanozudogru.restaurantservice.general.result;

public class ResultHelper {

    public static Result restaurantNotFound(String errorMessage) {
        return new Result(false, "400", errorMessage);
    }

}
