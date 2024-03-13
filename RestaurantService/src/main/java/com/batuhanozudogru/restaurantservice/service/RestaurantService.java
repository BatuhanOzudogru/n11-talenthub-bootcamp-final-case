package com.batuhanozudogru.restaurantservice.service;



import com.batuhanozudogru.restaurantservice.dao.RestaurantRepository;
import com.batuhanozudogru.restaurantservice.entity.Restaurant;
import com.batuhanozudogru.restaurantservice.general.exception.RestaurantNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void deleteAllRestaurants() {
        restaurantRepository.deleteAll();
    }


    public Iterable<Restaurant> getAllRestaurants() {
        Iterable<Restaurant> all = restaurantRepository.findAll();
        return all;
    }
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurantById(String id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public void deleteRestaurantById(String id) {
        restaurantRepository.deleteById(id);
    }




}
