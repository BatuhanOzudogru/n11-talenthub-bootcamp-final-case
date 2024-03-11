package com.batuhanozudogru.userservice.client;

import com.batuhanozudogru.userservice.dto.request.RestaurantSaveRequest;
import com.batuhanozudogru.userservice.dto.response.RestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "restaurant-service", url = "http://localhost:8086/api/v1/restaurants")
public interface RestaurantClient {

    @GetMapping
    Iterable<RestaurantResponse> getRestaurants();

    @DeleteMapping("/deleteAll")
    void deleteAllRestaurants();

    @PostMapping("/save")
    RestaurantResponse saveRestaurant(@RequestBody RestaurantSaveRequest restaurant);

}
