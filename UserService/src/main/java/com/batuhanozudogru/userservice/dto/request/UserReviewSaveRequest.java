package com.batuhanozudogru.userservice.dto.request;
import com.batuhanozudogru.userservice.general.enums.ReviewRate;

public record UserReviewSaveRequest(String review,
                                    String restaurantId,
                                    ReviewRate rate,
                                    UserForReviewSaveRequest user) {
}
