package com.batuhanozudogru.userservice.controller.contract.impl;

import com.batuhanozudogru.userservice.client.RestaurantClient;
import com.batuhanozudogru.userservice.controller.contract.UserReviewControllerContract;
import com.batuhanozudogru.userservice.dto.UpdateReviewForRestaurantDTO;
import com.batuhanozudogru.userservice.dto.request.UserReviewForRestaurantRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewUpdateRequest;
import com.batuhanozudogru.userservice.dto.response.UserReviewResponse;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.entity.UserReview;
import com.batuhanozudogru.userservice.mapper.UserReviewMapper;
import com.batuhanozudogru.userservice.service.UserReviewService;
import com.batuhanozudogru.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserReviewControllerContractImpl implements UserReviewControllerContract {

    private final UserReviewService userReviewService;
    private final UserReviewMapper userReviewMapper;
    private final RestaurantClient restaurantClient;
    private final UserService userService;

    @Override
    public List<UserReviewResponse> getAllUserReviews() {

        log.info("Request received to get all user reviews.");

        List<UserReview> userReviews = userReviewService.getAllUserReviews();

        return userReviewMapper.convertToUserReviewResponseList(userReviews);
    }
    @Override
    public UserReviewResponse saveUserReview(UserReviewSaveRequest userReviewSaveRequest) {

        log.info("Request received to save user review: {}", userReviewSaveRequest);

        User user = userService.findById(userReviewSaveRequest.user().id());

        UserReview userReview = userReviewMapper.convertToUserReview(userReviewSaveRequest);

        userReview.getUser().setUsername(user.getUsername());

        UserReviewForRestaurantRequest userReviewForRestaurantRequest = userReviewMapper.convertToUserReviewForRestaurantRequest(userReview);

        UserReviewResponse userReviewResponse = userReviewMapper.convertToUserReviewResponse(userReviewService.save(userReview));

        if(!userReviewForRestaurantRequest.restaurantId().equals("test")){

            restaurantClient.addReviewToRestaurant(userReviewForRestaurantRequest);
        }

        return userReviewResponse;
    }

    @Override
    public UserReviewResponse getUserReviewById(Long id) {

        log.info("Request received to get user review by ID: {}", id);

        UserReview userReview = userReviewService.findById(id);

        return userReviewMapper.convertToUserReviewResponse(userReview);
    }

    @Override
    public UserReviewResponse updateUserReview(Long id, UserReviewUpdateRequest request) {

       log.info("Request received to update user review with ID: {}", id);

       UserReview oldUserReview = userReviewService.findById(id);

       UserReviewForRestaurantRequest oldDTO = userReviewMapper.convertToUserReviewForRestaurantRequest(oldUserReview);

       userReviewMapper.updateUserReview(oldUserReview, request);

       UserReview newUserReview = userReviewService.save(oldUserReview);

       UserReviewForRestaurantRequest newDTO = userReviewMapper.convertToUserReviewForRestaurantRequest(newUserReview);

       UpdateReviewForRestaurantDTO updateReviewForRestaurantDTO = userReviewMapper.convertToUpdateReviewForRestaurantDTO(oldDTO, newDTO);

        if(!updateReviewForRestaurantDTO.newReview().restaurantId().equals("test")){
            restaurantClient.updateReviewToRestaurant(updateReviewForRestaurantDTO);
        }

       return userReviewMapper.convertToUserReviewResponse(newUserReview);
    }

    @Override
    public void deleteUserReview(Long id) {

        log.info("Request received to delete user review with ID: {}", id);

        UserReview userReview = userReviewService.findById(id);

        User user = userService.findById(userReview.getUser().getId());

        userReview.getUser().setUsername(user.getUsername());

        UserReviewForRestaurantRequest userReviewForRestaurantRequest = userReviewMapper.convertToUserReviewForRestaurantRequest(userReview);

        userReviewService.deleteById(id);

        if(!userReviewForRestaurantRequest.restaurantId().equals("test")){
            restaurantClient.deleteReviewToRestaurant(userReviewForRestaurantRequest);
        }

    }

    @Override
    public List<UserReviewResponse> getUserReviewListByUserId(Long userId) {

        log.info("Request received to get user reviews by user ID: {}", userId);

        List<UserReview> userReviews = userReviewService.findByUserId(userId);

        return userReviewMapper.convertToUserReviewResponseList(userReviews);
    }

    @Override
    public List<UserReviewResponse> getUserReviewByRestaurantId(String restaurantId) {

        log.info("Request received to get user reviews by restaurant ID: {}", restaurantId);

        List<UserReview> userReviews = userReviewService.findByRestaurantId(restaurantId);

        return userReviewMapper.convertToUserReviewResponseList(userReviews);
    }

    @Override
    public UserReviewResponse getUserReviewByUserUsername(String username) {

        log.info("Request received to get user review by username: {}", username);

        UserReview userReview = userReviewService.findByUserUserName(username);

        return userReviewMapper.convertToUserReviewResponse(userReview);
    }
}
