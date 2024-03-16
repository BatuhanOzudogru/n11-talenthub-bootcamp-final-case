package com.batuhanozudogru.restaurantservice.service;



import com.batuhanozudogru.restaurantservice.dao.RestaurantRepository;
import com.batuhanozudogru.restaurantservice.dto.ReviewDTO;
import com.batuhanozudogru.restaurantservice.dto.UpdateReviewDTO;
import com.batuhanozudogru.restaurantservice.entity.Restaurant;
import com.batuhanozudogru.restaurantservice.general.exception.NullException;
import com.batuhanozudogru.restaurantservice.general.exception.RestaurantNotFoundException;
import com.batuhanozudogru.restaurantservice.general.message.PrefixFields;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service

public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {

        this.restaurantRepository = restaurantRepository;
    }

    public void deleteAllRestaurants() {

        restaurantRepository.deleteAll();
    }


    public List<Restaurant> getAllRestaurants() {

        return StreamSupport.stream(restaurantRepository.findAll().spliterator(), false)
                .toList();
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

        restaurant.getComments().add(
                PrefixFields.USER + reviewDTO.username() + " " +
                PrefixFields.COMMENT + reviewDTO.review() + " " +
                PrefixFields.RATING + reviewDTO.rate() + " " +
                PrefixFields.RESTAURANT + restaurant.getName() + " " +
                PrefixFields.RESTAURANT_ID + restaurant.getId()
        );
        restaurantRepository.save(restaurant);
    }

    public void updateReviewToRestaurant(UpdateReviewDTO updateReviewDTO) {

        Restaurant restaurant = getRestaurantById(updateReviewDTO.oldReview().restaurantId());

        List<String> comments = restaurant.getComments();

        comments.remove(
                PrefixFields.USER + updateReviewDTO.oldReview().username() + " " +
                PrefixFields.COMMENT + updateReviewDTO.oldReview().review() + " " +
                PrefixFields.RATING + updateReviewDTO.oldReview().rate() + " " +
                PrefixFields.RESTAURANT + restaurant.getName() + " " +
                PrefixFields.RESTAURANT_ID + restaurant.getId());

        comments.add(
                PrefixFields.USER + updateReviewDTO.newReview().username() + " " +
                PrefixFields.COMMENT + updateReviewDTO.newReview().review() + " " +
                PrefixFields.RATING + updateReviewDTO.newReview().rate() + " " +
                PrefixFields.RESTAURANT + restaurant.getName() + " " +
                PrefixFields.RESTAURANT_ID + restaurant.getId()
        );

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

    public List<Restaurant> getByRestaurantName(String name) {

        List<Restaurant> restaurants = restaurantRepository.findByName(name);
        if(restaurants.isEmpty()){
            throw new RestaurantNotFoundException(name);
        }
        return restaurants;
    }

    public void validateRestaurant(Restaurant restaurant) {

        if(restaurant.getName() == null || restaurant.getAddress() == null || restaurant.getLatitude() == null || restaurant.getLongitude() == null || restaurant.getRate() == null){
            throw new NullException();
        }

    }
}
