package com.batuhanozudogru.restaurantservice.controller;


import com.batuhanozudogru.restaurantservice.controller.contract.RestaurantControllerContract;
import com.batuhanozudogru.restaurantservice.dto.ReviewDTO;
import com.batuhanozudogru.restaurantservice.dto.UpdateReviewDTO;
import com.batuhanozudogru.restaurantservice.dto.request.RestaurantRequest;
import com.batuhanozudogru.restaurantservice.dto.response.RestaurantResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/restaurants")

public class RestaurantController {


    private final RestaurantControllerContract contract;

    public RestaurantController(RestaurantControllerContract contract) {
        this.contract = contract;
    }

    @GetMapping
    public Iterable<RestaurantResponse> getAllRestaurants() {

        return contract.getRestaurants();
    }

    @PostMapping("/save")
    public RestaurantResponse saveRestaurant(@RequestBody RestaurantRequest restaurant) {

        return contract.saveRestaurant(restaurant);
    }

    @GetMapping("/get-by-id/{id}")
    public RestaurantResponse getRestaurantById(@PathVariable String id) {

        return contract.getRestaurantById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRestaurantById(@PathVariable String id) {

        contract.deleteRestaurant(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllRestaurants() {

        contract.deleteAllRestaurants();
    }

    @PutMapping("/update/{id}")
    public RestaurantResponse updateRestaurant(@PathVariable String id,@RequestBody RestaurantRequest request) {

        return contract.updateRestaurant(id, request);
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


}
