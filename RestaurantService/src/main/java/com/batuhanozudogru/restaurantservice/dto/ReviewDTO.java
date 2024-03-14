package com.batuhanozudogru.restaurantservice.dto;

public record ReviewDTO(String review,
                        String restaurantId,
                        String rate,
                        String username) {
}
