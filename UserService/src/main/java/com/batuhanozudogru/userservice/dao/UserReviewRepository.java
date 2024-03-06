package com.batuhanozudogru.userservice.dao;

import com.batuhanozudogru.userservice.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}
