package com.batuhanozudogru.restaurantservice.controller.contract.impl;


import com.batuhanozudogru.restaurantservice.controller.contract.RestaurantControllerContract;
import com.batuhanozudogru.restaurantservice.dto.ReviewDTO;
import com.batuhanozudogru.restaurantservice.dto.UpdateReviewDTO;
import com.batuhanozudogru.restaurantservice.entity.Restaurant;
import com.batuhanozudogru.restaurantservice.mapper.RestaurantMapper;
import com.batuhanozudogru.restaurantservice.dto.request.RestaurantRequest;
import com.batuhanozudogru.restaurantservice.dto.response.RestaurantResponse;
import com.batuhanozudogru.restaurantservice.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantControllerContractImpl implements RestaurantControllerContract {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    public RestaurantControllerContractImpl(RestaurantService restaurantService, RestaurantMapper restaurantMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }


    @Override
    public void deleteAllRestaurants() {
        restaurantService.deleteAllRestaurants();
    }

    @Override
    public List<RestaurantResponse> getRestaurants() {
       Iterable<Restaurant> restaurants = restaurantService.getAllRestaurants();



        return restaurantMapper.convertToRestaurantResponseList(restaurants);

    }

    @Override
    public RestaurantResponse getRestaurantById(String id) {

        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return restaurantMapper.convertToRestaurantResponse(restaurant);
    }

    @Override
    public RestaurantResponse saveRestaurant(RestaurantRequest request) {



        Restaurant restaurant = restaurantMapper.convertToRestaurant(request);
        Restaurant savedRestaurant = restaurantService.saveRestaurant(restaurant);
        return restaurantMapper.convertToRestaurantResponse(savedRestaurant);
    }

    @Override
    public RestaurantResponse updateRestaurant(String id, RestaurantRequest request) {

        Restaurant restaurant = restaurantService.getRestaurantById(id);
        restaurantMapper.updateRestaurant(restaurant, request);
        Restaurant updatedRestaurant = restaurantService.saveRestaurant(restaurant);
        return restaurantMapper.convertToRestaurantResponse(updatedRestaurant);

    }

    @Override
    public void addReviewToRestaurant(ReviewDTO reviewDTO) {

        restaurantService.addReviewToRestaurant(reviewDTO);
    }

    @Override
    public void updateReviewToRestaurant(UpdateReviewDTO reviewDTO) {

        restaurantService.updateReviewToRestaurant(reviewDTO);
    }

    @Override
    public void deleteReviewToRestaurant(ReviewDTO reviewDTO) {

        restaurantService.deleteReviewToRestaurant(reviewDTO);
    }

    @Override
    public void deleteRestaurant(String id) {

        restaurantService.deleteRestaurantById(id);
    }
}
