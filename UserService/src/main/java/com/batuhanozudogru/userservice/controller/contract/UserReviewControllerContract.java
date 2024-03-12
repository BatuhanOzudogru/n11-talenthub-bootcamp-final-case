package com.batuhanozudogru.userservice.controller.contract;

import com.batuhanozudogru.userservice.dto.request.UserReviewSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewUpdateRequest;
import com.batuhanozudogru.userservice.dto.response.UserReviewResponse;
import com.batuhanozudogru.userservice.entity.UserReview;

import java.util.List;

public interface UserReviewControllerContract {

    List<UserReviewResponse> getAllUserReviews();
    UserReviewResponse saveUserReview(UserReviewSaveRequest userReviewSaveRequest);

    UserReviewResponse getUserReviewById(Long id);

    UserReviewResponse updateUserReview(Long id, UserReviewUpdateRequest request);

    void deleteUserReview(Long id);

    List<UserReviewResponse> getUserReviewListByUserId(Long userId);

    List<UserReviewResponse> getUserReviewByRestaurantId(String restaurantId);

    UserReviewResponse getUserReviewByUserUsername(String username);


}
