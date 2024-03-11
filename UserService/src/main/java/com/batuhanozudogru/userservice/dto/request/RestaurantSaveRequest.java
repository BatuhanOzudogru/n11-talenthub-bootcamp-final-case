package com.batuhanozudogru.userservice.dto.request;

public record RestaurantSaveRequest(String name,
                                    String address,
                                    String latitude,
                                    String longitude,

                                    String rate) {
}
