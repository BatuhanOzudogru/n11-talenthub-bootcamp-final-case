package com.batuhanozudogru.restaurantservice.general;

import com.batuhanozudogru.restaurantservice.general.exception.RestaurantNotFoundException;
import com.batuhanozudogru.restaurantservice.general.message.Message;
import com.batuhanozudogru.restaurantservice.general.result.Result;
import com.batuhanozudogru.restaurantservice.general.result.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Result> handleRestaurantNotFoundException(String id) {

        return new ResponseEntity<>(ResultHelper.restaurantNotFound(Message.RESTAURANT_NOT_FOUND_BY_ID(id)), HttpStatus.BAD_REQUEST);

    }
}
