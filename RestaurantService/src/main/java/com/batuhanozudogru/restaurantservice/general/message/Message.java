package com.batuhanozudogru.restaurantservice.general.message;



public class Message {


    public static final String RESTAURANT_NOT_FOUND_BY_ID(String id) {
        return "Restaurant with id '" + id + "' not found";
    }

    public static final String RATE_NOT_VALID = "Rate must be between 1 and 5";

    public static final String NOT_NULL = "Fields cannot be null";


}
