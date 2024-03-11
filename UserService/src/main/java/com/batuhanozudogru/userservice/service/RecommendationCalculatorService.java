package com.batuhanozudogru.userservice.service;

import java.math.BigDecimal;

public class RecommendationCalculatorService {
    public static final double MAX_RESTAURANT_DISTANCE = 10.0;
    public static final double RATE_WEIGHT = 0.7;
    public static final double DISTANCE_WEIGHT = 0.3;

    public static BigDecimal calculateRecommendationScore(String restaurantRate, double restaurantDistance) {
        double restaurantRateDouble = Double.parseDouble(restaurantRate);

        double restaurantRateWeight = restaurantRateDouble * 20;

        BigDecimal restaurantDistanceWeight = BigDecimal.valueOf((MAX_RESTAURANT_DISTANCE - restaurantDistance) * 10);

        BigDecimal recommendationScore = BigDecimal.valueOf(RATE_WEIGHT * restaurantRateWeight)

                .add(BigDecimal.valueOf(DISTANCE_WEIGHT).multiply(restaurantDistanceWeight));

        return recommendationScore;
    }
}
