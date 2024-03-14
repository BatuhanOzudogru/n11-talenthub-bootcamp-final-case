package com.batuhanozudogru.restaurantservice.dto.response;

import com.batuhanozudogru.restaurantservice.dto.ReviewDTO;

import java.util.List;

public record RestaurantResponse(String id,
                                 String name,
                                 String address,
                                 String latitude,
                                 String longitude,
                                 String rate,
                                 List<String> reviews) {
}
