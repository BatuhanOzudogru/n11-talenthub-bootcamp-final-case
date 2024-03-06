package com.batuhanozudogru.userservice.general.exception;

public class FieldLengthExceededException extends RuntimeException{
    public FieldLengthExceededException(String message) {
        super(message);
    }
}
