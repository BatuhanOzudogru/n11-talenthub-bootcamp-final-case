package com.batuhanozudogru.restaurantservice.dto.request;

public record RestaurantRequest( String name,
                                 String address,
                                 Double latitude,
                                 Double longitude,
                                 String rate) {
}
