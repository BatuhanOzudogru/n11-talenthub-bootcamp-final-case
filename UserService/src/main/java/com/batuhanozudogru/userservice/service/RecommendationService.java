package com.batuhanozudogru.userservice.service;

import com.batuhanozudogru.userservice.dto.response.RestaurantResponse;
import com.batuhanozudogru.userservice.entity.User;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.batuhanozudogru.userservice.service.CoordinateDistanceCalculatorService.calculateDistance;

public class RecommendationService {

    public static Map<RestaurantResponse, Double> asd(Iterable<RestaurantResponse> restaurants, User user) {
        Map<RestaurantResponse, Double> map = new HashMap<>();

        restaurants.forEach(restaurantResponse -> {
            double distance = calculateDistance(user.getLatitude(), user.getLongitude(), restaurantResponse.latitude(), restaurantResponse.longitude());
            if (distance <= 10.0){
                map.put(restaurantResponse, distance);
            }

        });

        return map;
    }

    public static Map<String, Long> recommendList(Iterable<RestaurantResponse> restaurants, User user){
        Map<RestaurantResponse, Double> userlaolanmesafeler = asd(restaurants, user);
        Map<String, Long> recommendedList = new HashMap<>();

        userlaolanmesafeler.forEach((restaurantResponse, distance) -> {
            BigDecimal recommendationScore = RecommendationCalculatorService.calculateRecommendationScore(restaurantResponse.rate(), distance);
            recommendedList.put("Restaurant Name " + restaurantResponse.name() +
                            " Address: " + restaurantResponse.address() +
                            " Rate: " + restaurantResponse.rate(),
                    recommendationScore.longValue());
        });


        List<Map.Entry<String, Long>> sortedEntries = recommendedList.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toList());

        Map<String, Long> top3Recommendations = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry : sortedEntries) {
            top3Recommendations.put(entry.getKey(), entry.getValue());
        }

        return top3Recommendations;
    }
}
