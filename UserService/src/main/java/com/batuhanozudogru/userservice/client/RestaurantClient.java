package com.batuhanozudogru.userservice.client;

import com.batuhanozudogru.userservice.dto.UpdateReviewForRestaurantDTO;
import com.batuhanozudogru.userservice.dto.request.RestaurantSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewForRestaurantRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewSaveRequest;
import com.batuhanozudogru.userservice.dto.response.RestaurantResponse;
import com.batuhanozudogru.userservice.general.result.Result;
import com.batuhanozudogru.userservice.general.result.ResultData;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "restaurant-service", url = "http://localhost:8086/api/v1/restaurants")
public interface RestaurantClient {

    @GetMapping
    ResultData<List<RestaurantResponse>> getRestaurants();

    @DeleteMapping("/deleteAll")
    Result deleteAllRestaurants();

    @PostMapping("/save")
    ResultData<RestaurantResponse> saveRestaurant(@RequestBody RestaurantSaveRequest restaurant);

    @PutMapping("/update/{id}")
    ResultData<RestaurantResponse> updateRestaurant(@PathVariable String id,@RequestBody RestaurantSaveRequest request);

    @GetMapping("/get-by-id/{id}")
    ResultData<RestaurantResponse> getRestaurantById(@PathVariable("id") String id);

    @PostMapping("/add-review")
    Result addReviewToRestaurant(@RequestBody UserReviewForRestaurantRequest reviewDTO);

    @PutMapping("/update-review")
    Result updateReviewToRestaurant(@RequestBody UpdateReviewForRestaurantDTO reviewDTOs);

    @DeleteMapping("/delete-review")
    Result deleteReviewToRestaurant(@RequestBody UserReviewForRestaurantRequest reviewDTO);

}
