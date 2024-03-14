package com.batuhanozudogru.restaurantservice.mapper;


import com.batuhanozudogru.restaurantservice.dto.ReviewDTO;
import com.batuhanozudogru.restaurantservice.dto.request.RestaurantRequest;
import com.batuhanozudogru.restaurantservice.dto.response.RestaurantResponse;
import com.batuhanozudogru.restaurantservice.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class RestaurantMapper {

    public Restaurant convertToRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.name());
        restaurant.setAddress(restaurantRequest.address());
        restaurant.setLatitude(String.valueOf(restaurantRequest.latitude()));
        restaurant.setLongitude(String.valueOf(restaurantRequest.longitude()));
        restaurant.setRate(restaurantRequest.rate());
        return restaurant;
    }

    public RestaurantResponse convertToRestaurantResponse(Restaurant restaurant) {

        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getLatitude(),
                restaurant.getLongitude(),
                restaurant.getRate(),
                restaurant.getComments()
        );
    }

    public List<RestaurantResponse> convertToRestaurantResponseList(Iterable<Restaurant> restaurants) {
        List<RestaurantResponse> responseList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            responseList.add(convertToRestaurantResponse(restaurant));
        }
        return responseList;
    }

    public void updateRestaurant(Restaurant restaurant, RestaurantRequest restaurantRequest) {
        restaurant.setName(restaurantRequest.name());
        restaurant.setAddress(restaurantRequest.address());
        restaurant.setLatitude(String.valueOf(restaurantRequest.latitude()));
        restaurant.setLongitude(String.valueOf(restaurantRequest.longitude()));
        restaurant.setRate(restaurantRequest.rate());
    }
}