package com.batuhanozudogru.restaurantservice.dto.response;

public record RestaurantResponse(String id,
                                 String name,
                                 String address,
                                 String latitude,
                                 String longitude,
                                 String rate) {
}
