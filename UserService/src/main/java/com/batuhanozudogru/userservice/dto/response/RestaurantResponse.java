package com.batuhanozudogru.userservice.dto.response;

import java.util.List;

public record RestaurantResponse(String id,
                                 String name,
                                 String address,
                                 String latitude,
                                 String longitude,
                                 String rate,
                                 List<String> comments) {
}
