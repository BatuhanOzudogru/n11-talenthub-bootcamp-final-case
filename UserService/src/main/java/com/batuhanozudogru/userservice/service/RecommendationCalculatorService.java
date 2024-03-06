package com.batuhanozudogru.userservice.service;

import java.math.BigDecimal;

public class RecommendationCalculatorService {
    public static final double MAX_RESTAURANT_DISTANCE = 10.0;
    public static final double RATE_WEIGHT = 0.7;
    public static final double DISTANCE_WEIGHT = 0.3;

    public static BigDecimal calculateRecommendationScore(double restaurantRate, BigDecimal restaurantDistance) {


        double restaurantRateWeight = restaurantRate * 20;

        BigDecimal restourantDistanceWeight = BigDecimal.valueOf((MAX_RESTAURANT_DISTANCE - restaurantDistance.doubleValue()) * 10);

        BigDecimal recommendationScore = BigDecimal.valueOf(RATE_WEIGHT * restaurantRateWeight)
                .add(BigDecimal.valueOf(DISTANCE_WEIGHT).multiply(restourantDistanceWeight));

        return recommendationScore;
    }
}
