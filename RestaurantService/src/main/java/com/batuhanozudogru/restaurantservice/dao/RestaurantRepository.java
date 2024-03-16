package com.batuhanozudogru.restaurantservice.dao;

import com.batuhanozudogru.restaurantservice.entity.Restaurant;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends SolrCrudRepository<Restaurant, String>{
    @Query("name:*?0*")
    List<Restaurant> findByName(String name);

}
