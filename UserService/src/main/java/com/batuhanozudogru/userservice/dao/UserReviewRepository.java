package com.batuhanozudogru.userservice.dao;

import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {


    Optional<UserReview> findByRestaurantId(String restaurantId);

    Optional<UserReview> findByUser(User user);

    Optional<UserReview> findByUser_Username (String username);

    Optional<UserReview> findByUser_Id (Long id);
}
