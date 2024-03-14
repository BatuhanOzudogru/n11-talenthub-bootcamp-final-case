package com.batuhanozudogru.restaurantservice.general.result;

import com.batuhanozudogru.restaurantservice.general.message.Message;

public class ResultHelper {

    public static Result restaurantNotFound(String errorMessage) {
        return new Result(false, "400", errorMessage);
    }

    public static Result rateError() {
        return new Result(false, "400", Message.RATE_NOT_VALID);
    }

    public static Result notNullError() {
        return new Result(false, "400", Message.NOT_NULL);
    }
}
