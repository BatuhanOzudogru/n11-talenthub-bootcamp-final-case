package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.client.RestaurantClient;
import com.batuhanozudogru.userservice.dao.UserRepository;
import com.batuhanozudogru.userservice.dto.request.RestaurantSaveRequest;
import com.batuhanozudogru.userservice.dto.response.RestaurantResponse;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.general.exception.UserNotFoundException;
import com.batuhanozudogru.userservice.general.message.Message;
import com.batuhanozudogru.userservice.service.RecommendationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

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

        User user = userRepository.findById(userId).orElseThrow(()
                -> new UserNotFoundException(Message.USER_NOT_FOUND_BY_ID(userId)));

        Map<String, Long> recommendList = RecommendationService.recommendList(restaurants, user);

        return recommendList;
    }

    @DeleteMapping("/delete-all")
    public void deleteAllRestaurants() {

        restaurantClient.deleteAllRestaurants();
    }

    @PostMapping("/save")
    public RestaurantResponse saveRestaurant(@RequestBody @Valid RestaurantSaveRequest request) {

        return restaurantClient.saveRestaurant(request);
    }

    @PutMapping("/update{id}")
    public RestaurantResponse updateRestaurant(@PathVariable String id, @RequestBody @Valid RestaurantSaveRequest request) {

        return restaurantClient.updateRestaurant(id, request);
    }

    @GetMapping("/get-by-id/{id}")
    public RestaurantResponse getById(@PathVariable("id") String id) {

        return restaurantClient.getRestaurantById(id);
    }
}
