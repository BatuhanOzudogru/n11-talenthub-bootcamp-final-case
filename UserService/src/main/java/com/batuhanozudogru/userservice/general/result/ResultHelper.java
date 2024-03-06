package com.batuhanozudogru.userservice.general.result;

import com.batuhanozudogru.userservice.general.message.Message;

public class ResultHelper {

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



//    public static <T> ResultData<T> created(T data) {
//        return new ResultData<>(true, "201", Message.CREATED, data);
//    }
//
//    public static <T> ResultData<T> updated(T data) {
//        return new ResultData<>(true, "200", Message.UPDATED, data);
//    }
//
//
//    public static <T> ResultData<T> validateError(T data) {
//        return new ResultData<>(false, "400", Message.VALIDATE_ERROR, data);
//    }
//
//    public static Result deleted() {
//        return new Result(true, "200", Message.DELETED);
//    }
//
//    public static Result notFoundError() {
//        return new Result(false, "404", Message.NOT_FOUND);
//    }

//    public static Result localDateError() {
//        return new Result(false, "400", Message.DATE_ERROR);
//    }
//
//    public static Result vaccineError() {
//        return new Result(false, "400", Message.VACCINE_ERROR);
//    }
//
//    public static Result doctorError() {
//        return new Result(false, "400", Message.DOCTOR_ERROR);
//    }
//
//    public static Result appointmentTimeError() {
//        return new Result(false, "400", Message.APP0_TIME_ERROR);
//    }
//
//    public static Result appointmentExistsError() {
//        return new Result(false, "400", Message.APP0_EXIST_ERROR);
//    }
//
//    public static Result appointmentNotAvailableError() {
//        return new Result(false, "400", Message.APP0_NOT_AVA_ERROR);
//    }
//
//    public static <T> ResultData<T> success(T data) {
//        return new ResultData<>(true, "200", Message.OK, data);
//    }
}

    
