package com.batuhanozudogru.userservice.general.result;

import com.batuhanozudogru.userservice.general.message.Message;

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

    public static Result allDeleted() {
        return new Result(true, "200", Message.ALL_DELETED);
    }


    public static Result hardDeleted() {
        return new Result(true, "200", Message.HARD_DELETED);
    }

    public static Result softDeleted() {
        return new Result(true, "200", Message.SOFT_DELETED);
    }

    public static Result activated() {
        return new Result(true, "200", Message.ACTIVATED);
    }


    public static Result error(String errorMessage) {
        return new Result(false, "400", errorMessage);
    }
  
    public static Result usernameTakenResult() {
        return new Result(false, "400", Message.USERNAME_TAKEN);
    }

    public static Result turkishRepublicIdNoAlreadyExist() {
        return new Result(false, "400", Message.TURKISH_REPUBLIC_ID_NO_ALREADY_EXIST);
    }

    public static Result turkishRepublicIdNoCanNotBeVerified() {
        return new Result(false, "400", Message.TURKISH_REPUBLIC_ID_CAN_NOT_BE_VERIFIED);
    }
}

    
