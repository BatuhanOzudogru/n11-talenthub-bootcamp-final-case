package com.batuhanozudogru.userservice.dto.response;

import com.batuhanozudogru.userservice.entity.UserReview;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record UserResponse(Long id,
                           String firstName,
                           String lastName,
                           String turkishRepublicIdNumber,
                           String username,
                           LocalDate birthDate,
                           BigDecimal latitude,
                           BigDecimal longitude,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt,
                           List<UserReview> reviews
                          ) {
}
