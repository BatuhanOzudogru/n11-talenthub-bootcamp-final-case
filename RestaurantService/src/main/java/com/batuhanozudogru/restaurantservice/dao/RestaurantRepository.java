package com.batuhanozudogru.restaurantservice.dao;

import com.batuhanozudogru.restaurantservice.entity.Restaurant;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends SolrCrudRepository<Restaurant, String>{
    @Query("name:*?0*")
    Iterable<Restaurant> findByName(String name);

}
