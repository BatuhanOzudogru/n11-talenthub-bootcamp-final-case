package com.batuhanozudogru.userservice.dto.request;

import java.math.BigDecimal;

public record UserUpdateRequest(String username,
                                BigDecimal latitude,
                                BigDecimal longitude) {
}
