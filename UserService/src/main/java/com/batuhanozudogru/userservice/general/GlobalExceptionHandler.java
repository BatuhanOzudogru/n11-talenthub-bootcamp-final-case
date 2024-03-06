package com.batuhanozudogru.userservice.general;

import com.batuhanozudogru.userservice.general.exception.*;
import com.batuhanozudogru.userservice.general.result.Result;
import com.batuhanozudogru.userservice.general.result.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameTakenException.class)
    public ResponseEntity<Result> handleUserAlreadyExistException() {

       return new ResponseEntity<>(ResultHelper.usernameTakenResult(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(TurkishRepublicIdNoAlreadyExistException.class)
    public ResponseEntity<Result> handleTurkishRepublicIdNoAlreadyExistException() {

        return new ResponseEntity<>(ResultHelper.turkishRepublicIdNoAlreadyExist(), HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(TurkishRepublicIdNoCanNotBeVerifiedException.class)
    public ResponseEntity<Result> handleturkishRepublicIdNoCanNotBeVerified() {

        return new ResponseEntity<>(ResultHelper.turkishRepublicIdNoCanNotBeVerified(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Result> handleUserNotFoundException(UserNotFoundException ex) {

        return new ResponseEntity<>(ResultHelper.error(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FieldLengthExceededException.class)
    public ResponseEntity<Result> handleFieldLengthExceededException(FieldLengthExceededException ex) {

        return new ResponseEntity<>(ResultHelper.error(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
