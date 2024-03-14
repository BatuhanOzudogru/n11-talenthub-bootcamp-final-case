package com.batuhanozudogru.restaurantservice.general.result;

import com.batuhanozudogru.restaurantservice.general.message.Message;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, "201", Message.CREATED, data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, "200", Message.OK, data);
    }

    public static <T> ResultData<T> updated(T data) {
        return new ResultData<>(true, "200", Message.UPDATED, data);
    }

    public static Result deleted() {
        return new Result(true, "200", Message.DELETED);
    }

    public static Result allDeleted() {
        return new Result(true, "200", Message.ALL_DELETED);
    }

    public static Result success() {
        return new Result(true, "200", Message.OK);
    }
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
