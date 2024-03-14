package com.batuhanozudogru.userservice.client;

import com.batuhanozudogru.userservice.dto.request.RestaurantSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewForRestaurantRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewSaveRequest;
import com.batuhanozudogru.userservice.dto.response.RestaurantResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "restaurant-service", url = "http://localhost:8086/api/v1/restaurants")
public interface RestaurantClient {

    @GetMapping
    Iterable<RestaurantResponse> getRestaurants();

    @DeleteMapping("/deleteAll")
    void deleteAllRestaurants();

    @PostMapping("/save")
    RestaurantResponse saveRestaurant(@RequestBody RestaurantSaveRequest restaurant);

    @PutMapping("/update/{id}")
    RestaurantResponse updateRestaurant(@PathVariable String id,@RequestBody RestaurantSaveRequest request);

    @GetMapping("/get-by-id/{id}")
    RestaurantResponse getRestaurantById(@PathVariable("id") String id);

    @PostMapping("/add-review")
    void addReviewToRestaurant(@RequestBody UserReviewForRestaurantRequest reviewDTO);

    @DeleteMapping("/delete-review")
    void deleteReviewToRestaurant(@RequestBody UserReviewForRestaurantRequest reviewDTO);

}
