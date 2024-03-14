package com.batuhanozudogru.restaurantservice.controller.contract;



import com.batuhanozudogru.restaurantservice.dto.ReviewDTO;
import com.batuhanozudogru.restaurantservice.dto.request.RestaurantRequest;
import com.batuhanozudogru.restaurantservice.dto.response.RestaurantResponse;

public interface RestaurantControllerContract {

    void deleteAllRestaurants();

    Iterable<RestaurantResponse> getRestaurants();

    RestaurantResponse getRestaurantById(String id);

    RestaurantResponse saveRestaurant(RestaurantRequest request);

    RestaurantResponse updateRestaurant(String id, RestaurantRequest request);

    void addReviewToRestaurant(ReviewDTO reviewDTO);

    void deleteReviewToRestaurant(ReviewDTO reviewDTO);

    void deleteRestaurant(String id);
}