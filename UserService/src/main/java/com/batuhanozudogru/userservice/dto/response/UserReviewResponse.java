package com.batuhanozudogru.userservice.dto.response;

import com.batuhanozudogru.userservice.general.enums.ReviewRate;

import java.time.LocalDateTime;

public record UserReviewResponse(Long restaurantId,
                                 String review,
                                 String username,
                                 ReviewRate rate,
                                 LocalDateTime createdAt,
                                 LocalDateTime updatedAt) {
}
