package com.batuhanozudogru.userservice.dto.request;

import com.batuhanozudogru.userservice.general.enums.ReviewRate;

public record UserReviewUpdateRequest(String review,
                                      ReviewRate rate) {
}
