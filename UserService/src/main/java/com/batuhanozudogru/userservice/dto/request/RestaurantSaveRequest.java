package com.batuhanozudogru.userservice.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record RestaurantSaveRequest(String name,
                                    String address,
                                    @Min(-90) @Max(90) double latitude,
                                    @Min(-180) @Max(180)double longitude,
                                    String rate
                                    ) {
}
