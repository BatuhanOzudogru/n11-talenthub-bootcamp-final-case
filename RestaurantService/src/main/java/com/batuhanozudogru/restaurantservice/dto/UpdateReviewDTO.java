package com.batuhanozudogru.restaurantservice.dto;

public record UpdateReviewDTO(ReviewDTO oldReview,
                              ReviewDTO newReview) {
}
