package com.batuhanozudogru.userservice.general;

import com.batuhanozudogru.userservice.general.exception.*;
import com.batuhanozudogru.userservice.general.result.Result;
import com.batuhanozudogru.userservice.general.result.ResultHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public final ResponseEntity<Result> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        String errorMessage = e.getMessage();


        return new ResponseEntity<>(ResultHelper.error(errorMessage), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public final ResponseEntity<Result> handleValidExceptions(MethodArgumentNotValidException e) {

        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        String fieldName = e.getBindingResult().getFieldErrors().get(0).getField();


        return new ResponseEntity<>(ResultHelper.error(fieldName+" "+errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler ResponseEntity<Result> handleDataIntegrityViolationException(DataIntegrityViolationException e) {

        String errorMessage=e.getMostSpecificCause().getMessage();

        return new ResponseEntity<>(ResultHelper.error(errorMessage), HttpStatus.BAD_REQUEST);
    }

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

    @ExceptionHandler(UserReviewNotFoundException.class)
    public ResponseEntity<Result> handleUserReviewNotFoundException(FieldLengthExceededException ex) {

        return new ResponseEntity<>(ResultHelper.error(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}
