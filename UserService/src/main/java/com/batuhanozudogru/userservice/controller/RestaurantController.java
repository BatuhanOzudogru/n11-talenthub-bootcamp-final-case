package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.client.RestaurantClient;
import com.batuhanozudogru.userservice.dao.UserRepository;
import com.batuhanozudogru.userservice.dto.request.RestaurantSaveRequest;
import com.batuhanozudogru.userservice.dto.response.RestaurantResponse;
import com.batuhanozudogru.userservice.entity.User;

import com.batuhanozudogru.userservice.service.RecommendationService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantClient restaurantClient;
    private final UserRepository userRepository;

    @GetMapping
    public Iterable<RestaurantResponse> getRestaurants() {
        return restaurantClient.getRestaurants();
    }

    @GetMapping("recommend-restaurants/{userId}")
    public Map<String, Long> getRecommendRestaurants(@RequestParam Long userId) {
        Iterable<RestaurantResponse> restaurants = restaurantClient.getRestaurants();
        Optional<User> user = userRepository.findById(userId);
        Map<String, Long> stringLongMap = RecommendationService.recommendList(restaurants, user.get());


        return stringLongMap;
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllRestaurants() {
        restaurantClient.deleteAllRestaurants();
    }

    @PostMapping("/save")
    public RestaurantResponse saveRestaurant(@RequestBody RestaurantSaveRequest request) {

        return restaurantClient.saveRestaurant(request);
    }
}
