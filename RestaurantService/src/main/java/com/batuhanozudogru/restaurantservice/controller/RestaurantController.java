package com.batuhanozudogru.restaurantservice.controller;


import com.batuhanozudogru.restaurantservice.controller.contract.RestaurantControllerContract;
import com.batuhanozudogru.restaurantservice.dto.ReviewDTO;
import com.batuhanozudogru.restaurantservice.dto.UpdateReviewDTO;
import com.batuhanozudogru.restaurantservice.dto.request.RestaurantRequest;
import com.batuhanozudogru.restaurantservice.dto.response.RestaurantResponse;
import com.batuhanozudogru.restaurantservice.general.result.ResultData;
import com.batuhanozudogru.restaurantservice.general.result.ResultHelper;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/restaurants")

public class RestaurantController {


    private final RestaurantControllerContract contract;

    public RestaurantController(RestaurantControllerContract contract) {
        this.contract = contract;
    }

    @GetMapping
    public ResultData<List<RestaurantResponse>> getAllRestaurants() {

        List<RestaurantResponse> restaurants = contract.getRestaurants();


        return ResultHelper.success(restaurants);

    }

    @PostMapping("/save")
    public ResultData<RestaurantResponse> saveRestaurant(@RequestBody RestaurantRequest restaurant) {

        return ResultHelper.created(contract.saveRestaurant(restaurant));
    }

    @GetMapping("/get-by-name/{name}")
    public ResultData<List<RestaurantResponse>> getRestaurantsByName(@PathVariable String name) {

        return ResultHelper.success(contract.getRestaurantsByName(name));
    }

    @GetMapping("/get-by-id/{id}")
    public ResultData<RestaurantResponse> getRestaurantById(@PathVariable String id) {

        return ResultHelper.success(contract.getRestaurantById(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRestaurantById(@PathVariable String id) {

        contract.deleteRestaurant(id);


    }

    @DeleteMapping("/delete-all")
    public void deleteAllRestaurants() {

        contract.deleteAllRestaurants();


    }

    @PutMapping("/update/{id}")
    public ResultData<RestaurantResponse> updateRestaurant(@PathVariable String id,@RequestBody RestaurantRequest request) {

        return ResultHelper.success(contract.updateRestaurant(id, request));
    }

    @PostMapping("/add-review")
    public void addReviewToRestaurant(@RequestBody ReviewDTO reviewDTO) {

        contract.addReviewToRestaurant(reviewDTO);


    }

    @PutMapping("/update-review")
    public void updateReviewToRestaurant(@RequestBody UpdateReviewDTO reviewDTOS) {

        contract.updateReviewToRestaurant(reviewDTOS);


    }

    @DeleteMapping("/delete-review")
    public void deleteReviewToRestaurant(@RequestBody ReviewDTO reviewDTO) {

        contract.deleteReviewToRestaurant(reviewDTO);


    }

    @DeleteMapping("/delete-restaurant/{id}")
    public void deleteById(@PathVariable String id) {

        contract.deleteById(id);


    }


}
