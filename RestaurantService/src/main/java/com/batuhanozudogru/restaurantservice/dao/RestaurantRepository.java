package com.batuhanozudogru.restaurantservice.dao;

import com.batuhanozudogru.restaurantservice.entity.Restaurant;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends SolrCrudRepository<Restaurant, String>{
    @Query("name:*?0*")
    List<Restaurant> findByName(String name);

    Optional<Restaurant> findByAddress(String address);

    Optional<Restaurant> findByLatitudeAndLongitude(String latitude, String longitude);
}
