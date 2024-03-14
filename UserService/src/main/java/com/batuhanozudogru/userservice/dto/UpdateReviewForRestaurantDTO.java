package com.batuhanozudogru.userservice.dto;

import com.batuhanozudogru.userservice.dto.request.UserReviewForRestaurantRequest;

public record UpdateReviewForRestaurantDTO(UserReviewForRestaurantRequest oldReview,
                                           UserReviewForRestaurantRequest newReview) {
}
