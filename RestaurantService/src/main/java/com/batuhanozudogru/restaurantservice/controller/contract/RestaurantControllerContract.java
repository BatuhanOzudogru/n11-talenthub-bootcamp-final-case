package com.batuhanozudogru.restaurantservice.controller.contract;



import com.batuhanozudogru.restaurantservice.dto.ReviewDTO;
import com.batuhanozudogru.restaurantservice.dto.UpdateReviewDTO;
import com.batuhanozudogru.restaurantservice.dto.request.RestaurantRequest;
import com.batuhanozudogru.restaurantservice.dto.response.RestaurantResponse;

import java.util.List;

public interface RestaurantControllerContract {

    void deleteAllRestaurants();

    List<RestaurantResponse> getRestaurants();

    RestaurantResponse getRestaurantById(String id);

    RestaurantResponse saveRestaurant(RestaurantRequest request);

    RestaurantResponse updateRestaurant(String id, RestaurantRequest request);

    List<RestaurantResponse> getRestaurantsByName(String name);

    void deleteById(String id);

    void addReviewToRestaurant(ReviewDTO reviewDTO);

    void updateReviewToRestaurant(UpdateReviewDTO reviewDTO);

    void deleteReviewToRestaurant(ReviewDTO reviewDTO);

    void deleteRestaurant(String id);
}
