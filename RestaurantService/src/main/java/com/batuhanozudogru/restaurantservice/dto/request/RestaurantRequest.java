package com.batuhanozudogru.restaurantservice.dto.request;

public record RestaurantRequest( String name,
                                 String address,
                                 double latitude,
                                 double longitude,
                                 String rate) {
}
