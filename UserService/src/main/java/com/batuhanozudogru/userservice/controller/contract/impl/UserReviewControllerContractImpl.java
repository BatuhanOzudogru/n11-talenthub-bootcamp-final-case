package com.batuhanozudogru.userservice.controller.contract.impl;

import com.batuhanozudogru.userservice.controller.contract.UserReviewControllerContract;
import com.batuhanozudogru.userservice.dto.request.UserReviewSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewUpdateRequest;
import com.batuhanozudogru.userservice.dto.response.UserReviewResponse;
import com.batuhanozudogru.userservice.entity.UserReview;
import com.batuhanozudogru.userservice.mapper.UserReviewMapper;
import com.batuhanozudogru.userservice.service.UserReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserReviewControllerContractImpl implements UserReviewControllerContract {

    private final UserReviewService userReviewService;
    private final UserReviewMapper userReviewMapper;

    @Override
    public List<UserReviewResponse> getAllUserReviews() {
        List<UserReview> userReviews = userReviewService.getAllUserReviews();
        return userReviewMapper.convertToUserReviewResponseList(userReviews);
    }
    @Override
    public UserReviewResponse saveUserReview(UserReviewSaveRequest userReviewSaveRequest) {
        UserReview userReview = userReviewMapper.convertToUserReview(userReviewSaveRequest);
        return userReviewMapper.convertToUserReviewResponse(userReviewService.save(userReview));
    }

    @Override
    public UserReviewResponse getUserReviewById(Long id) {
       UserReview userReview = userReviewService.findById(id);
         return userReviewMapper.convertToUserReviewResponse(userReview);
    }

    @Override
    public UserReviewResponse updateUserReview(Long id, UserReviewUpdateRequest request) {

       UserReview userReview = userReviewService.findById(id);
       userReviewMapper.updateUserReview(userReview, request);

       userReviewService.save(userReview);
       return userReviewMapper.convertToUserReviewResponse(userReview);
    }

    @Override
    public void deleteUserReview(Long id) {
        userReviewService.deleteById(id);
    }

    @Override
    public List<UserReviewResponse> getUserReviewListByUserId(Long userId) {

        List<UserReview> userReviews = userReviewService.findByUserId(userId);
        return userReviewMapper.convertToUserReviewResponseList(userReviews);
    }

    @Override
    public List<UserReviewResponse> getUserReviewByRestaurantId(String restaurantId) {

        List<UserReview> userReviews = userReviewService.findByRestaurantId(restaurantId);
        return userReviewMapper.convertToUserReviewResponseList(userReviews);
    }

    @Override
    public List<UserReviewResponse> getUserReviewByUserUsername(String username) {

        List<UserReview> userReviews = userReviewService.findByUserUserName(username);
        return userReviewMapper.convertToUserReviewResponseList(userReviews);
    }
}
