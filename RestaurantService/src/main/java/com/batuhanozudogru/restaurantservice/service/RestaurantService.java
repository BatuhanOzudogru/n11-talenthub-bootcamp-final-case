package com.batuhanozudogru.restaurantservice.service;



import com.batuhanozudogru.restaurantservice.dao.RestaurantRepository;
import com.batuhanozudogru.restaurantservice.dto.ReviewDTO;
import com.batuhanozudogru.restaurantservice.dto.UpdateReviewDTO;
import com.batuhanozudogru.restaurantservice.entity.Restaurant;
import com.batuhanozudogru.restaurantservice.general.exception.NullException;
import com.batuhanozudogru.restaurantservice.general.exception.RateException;
import com.batuhanozudogru.restaurantservice.general.exception.RestaurantNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        if(restaurant.getId()==null){
            validateRestaurant(restaurant);
        }
        return restaurantRepository.save(restaurant);
    }



    public Restaurant getRestaurantById(String id) {

        return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public void addReviewToRestaurant(ReviewDTO reviewDTO) {

        Restaurant restaurant = getRestaurantById(reviewDTO.restaurantId());
        if(restaurant.getComments() == null) {
            restaurant.setComments(new ArrayList<>());
        }
        restaurant.getComments().add("User: " + reviewDTO.username() + " Comment: " + reviewDTO.review() + " Rating: " + reviewDTO.rate()+ " Restaurant: " + restaurant.getName() + " RestaurantId: " + restaurant.getId());

        restaurantRepository.save(restaurant);
    }

    public void updateReviewToRestaurant(UpdateReviewDTO updateReviewDTO) {

        Restaurant restaurant = getRestaurantById(updateReviewDTO.oldReview().restaurantId());

        List<String> comments = restaurant.getComments();

        comments.remove("User: " + updateReviewDTO.oldReview().username()+ " Comment: " + updateReviewDTO.oldReview().review() + " Rating: " + updateReviewDTO.oldReview().rate() + " Restaurant: " + restaurant.getName() + " RestaurantId: " + restaurant.getId());

        comments.add("User: " + updateReviewDTO.newReview().username() + " Comment: " + updateReviewDTO.newReview().review() + " Rating: " + updateReviewDTO.newReview().rate() + " Restaurant: " + restaurant.getName() + " RestaurantId: " + restaurant.getId());

        restaurant.setComments(comments);

        restaurantRepository.save(restaurant);

    }

    public void deleteReviewToRestaurant(ReviewDTO reviewDTO){

        Restaurant restaurant = getRestaurantById(reviewDTO.restaurantId());

        List<String> comments = restaurant.getComments();

        comments.remove("User: " + reviewDTO.username() + " Comment: " + reviewDTO.review() + " Rating: " + reviewDTO.rate() + " Restaurant: " + restaurant.getName() + " RestaurantId: " + restaurant.getId());

        restaurant.setComments(comments);

        restaurantRepository.save(restaurant);

    }

    public void deleteRestaurantById(String id) {

        restaurantRepository.deleteById(id);
    }

    public void validateRestaurant(Restaurant restaurant) {

        if(restaurant.getName() == null || restaurant.getAddress() == null || restaurant.getLatitude() == null || restaurant.getLongitude() == null || restaurant.getRate() == null){
            throw new NullException();
        }

    }
}
