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
}

    
