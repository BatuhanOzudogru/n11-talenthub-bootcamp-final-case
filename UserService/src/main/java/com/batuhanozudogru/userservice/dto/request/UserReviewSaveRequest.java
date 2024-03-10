package com.batuhanozudogru.userservice.dto.request;

import com.batuhanozudogru.userservice.dto.response.UserUserReviewResponse;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.general.enums.ReviewRate;

public record UserReviewSaveRequest(String review,
                                    Long restaurantId,
                                    ReviewRate rate,
                                    Long userId) {
}
