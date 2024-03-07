package com.batuhanozudogru.userservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponse(String firstName,
                           String lastName,
                           String turkishRepublicIdNumber,
                           String username,
                           LocalDate birthDate,
                           BigDecimal latitude,
                           BigDecimal longitude,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
}
