package com.batuhanozudogru.restaurantservice.mapper;


import com.batuhanozudogru.restaurantservice.dto.request.RestaurantRequest;
import com.batuhanozudogru.restaurantservice.dto.response.RestaurantResponse;
import com.batuhanozudogru.restaurantservice.entity.Restaurant;
import com.batuhanozudogru.restaurantservice.general.exception.RateException;
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

    public List<RestaurantResponse> convertToRestaurantResponseList(List<Restaurant> restaurants) {
        List<RestaurantResponse> responseList = new ArrayList<>(restaurants.size());
        for (Restaurant restaurant : restaurants) {
            responseList.add(convertToRestaurantResponse(restaurant));
        }
        return responseList;
    }

    public void updateRestaurant(Restaurant restaurant, RestaurantRequest restaurantRequest) {

        if(restaurantRequest.name() != null){
            restaurant.setName(restaurantRequest.name());
        }

        if(restaurantRequest.address() != null){
            restaurant.setAddress(restaurantRequest.address());
        }

        if(restaurantRequest.latitude() != null){
            restaurant.setLatitude(String.valueOf(restaurantRequest.latitude()));
        }

        if(restaurantRequest.longitude() != null){
            restaurant.setLongitude(String.valueOf(restaurantRequest.longitude()));
        }

        String rate = String.valueOf(restaurantRequest.rate());
        if(restaurantRequest.rate() != null){

            if (!rate.equals("1") && !rate.equals("2") && !rate.equals("3") && !rate.equals("4") && !rate.equals("5")) {
                throw new RateException();
            }

        restaurant.setRate(restaurantRequest.rate());
        }







    }
}