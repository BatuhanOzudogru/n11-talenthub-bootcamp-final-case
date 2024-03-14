package com.batuhanozudogru.restaurantservice.general;

import com.batuhanozudogru.restaurantservice.general.exception.NullException;
import com.batuhanozudogru.restaurantservice.general.exception.RateException;
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

        return new ResponseEntity<>(ResultHelper.restaurantNotFound(Message.RESTAURANT_NOT_FOUND_BY_ID(id)), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(RateException.class)
    public ResponseEntity<Result> handleRateException() {

        return new ResponseEntity<>(ResultHelper.rateError(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NullException.class)
    public ResponseEntity<Result> handleNullException() {

        return new ResponseEntity<>(ResultHelper.notNullError(), HttpStatus.BAD_REQUEST);

    }

}
