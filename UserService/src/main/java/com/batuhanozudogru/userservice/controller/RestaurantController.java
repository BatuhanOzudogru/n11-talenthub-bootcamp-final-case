package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.client.RestaurantClient;
import com.batuhanozudogru.userservice.dao.UserRepository;
import com.batuhanozudogru.userservice.dto.request.RestaurantSaveRequest;
import com.batuhanozudogru.userservice.dto.response.RestaurantResponse;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.general.exception.UserNotFoundException;
import com.batuhanozudogru.userservice.general.message.Message;
import com.batuhanozudogru.userservice.general.result.Result;
import com.batuhanozudogru.userservice.general.result.ResultData;
import com.batuhanozudogru.userservice.general.result.ResultHelper;
import com.batuhanozudogru.userservice.service.RecommendationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantClient restaurantClient;

    private final UserRepository userRepository;

    @GetMapping
    public ResultData<List<RestaurantResponse>> getRestaurants() {

        List<RestaurantResponse> restaurants = restaurantClient.getRestaurants().getData();

        return ResultHelper.success(restaurants);
    }

    @GetMapping("recommend-restaurants/{userId}")
    public ResultData<Map<String, Long>> getRecommendRestaurants(@RequestParam Long userId) {

        Iterable<RestaurantResponse> restaurants = restaurantClient.getRestaurants().getData();

        User user = userRepository.findById(userId).orElseThrow(()
                -> new UserNotFoundException(Message.USER_NOT_FOUND_BY_ID(userId)));

        Map<String, Long> recommendList = RecommendationService.recommendList(restaurants, user);

        return ResultHelper.success(recommendList);
    }

    @DeleteMapping("/delete-all")
    public Result deleteAllRestaurants() {

        restaurantClient.deleteAllRestaurants();

        return ResultHelper.allDeleted();
    }

    @PostMapping("/save")
    public ResultData<RestaurantResponse> saveRestaurant(@RequestBody @Valid RestaurantSaveRequest request) {

        RestaurantResponse restaurantResponse = restaurantClient.saveRestaurant(request).getData();

        return ResultHelper.created(restaurantResponse);
    }

    @PutMapping("/update{id}")
    public ResultData<RestaurantResponse> updateRestaurant(@PathVariable String id, @RequestBody @Valid RestaurantSaveRequest request) {

        RestaurantResponse restaurantResponse = restaurantClient.updateRestaurant(id, request).getData();

        return ResultHelper.updated(restaurantResponse);
    }

    @GetMapping("/get-by-id/{id}")
    public ResultData<RestaurantResponse> getById(@PathVariable("id") String id) {

        RestaurantResponse restaurantResponse = restaurantClient.getRestaurantById(id).getData();

        return ResultHelper.success(restaurantResponse);
    }
}
