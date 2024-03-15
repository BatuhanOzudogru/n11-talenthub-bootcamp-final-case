package com.batuhanozudogru.userservice.dao;

import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {


    @Query("SELECT u FROM UserReview u WHERE u.restaurantId = ?1")
    List<UserReview> findByRestaurantId(String restaurantId);

    Optional<UserReview> findByUser(User user);

    Optional<UserReview> findByUser_Username (String username);

    Optional<UserReview> findByUser_Id (Long id);
}
