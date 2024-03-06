package com.batuhanozudogru.userservice.dto.response;

import java.time.LocalDateTime;

public record UserResponse(String firstName,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
}
