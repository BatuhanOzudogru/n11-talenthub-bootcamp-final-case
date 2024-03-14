package com.batuhanozudogru.userservice.dto.request;

public record UserReviewForRestaurantRequest(String review,
                                             String restaurantId,
                                             String rate,
                                             String username) {
}
