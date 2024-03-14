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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserReviewControllerContractImpl implements UserReviewControllerContract {

    private final UserReviewService userReviewService;
    private final UserReviewMapper userReviewMapper;
    private final RestaurantClient restaurantClient;
    private final UserService userService;

    @Override
    public List<UserReviewResponse> getAllUserReviews() {
        List<UserReview> userReviews = userReviewService.getAllUserReviews();

        return userReviewMapper.convertToUserReviewResponseList(userReviews);
    }
    @Override
    public UserReviewResponse saveUserReview(UserReviewSaveRequest userReviewSaveRequest) {

        User user = userService.findById(userReviewSaveRequest.user().id());

        UserReview userReview = userReviewMapper.convertToUserReview(userReviewSaveRequest);

        userReview.getUser().setUsername(user.getUsername());

        UserReviewForRestaurantRequest userReviewForRestaurantRequest = userReviewMapper.convertToUserReviewForRestaurantRequest(userReview);

        UserReviewResponse userReviewResponse = userReviewMapper.convertToUserReviewResponse(userReviewService.save(userReview));

        restaurantClient.addReviewToRestaurant(userReviewForRestaurantRequest);

        return userReviewResponse;
    }

    @Override
    public UserReviewResponse getUserReviewById(Long id) {

        UserReview userReview = userReviewService.findById(id);

        return userReviewMapper.convertToUserReviewResponse(userReview);
    }

    @Override
    public UserReviewResponse updateUserReview(Long id, UserReviewUpdateRequest request) {

       UserReview oldUserReview = userReviewService.findById(id);

       UserReviewForRestaurantRequest oldDTO = userReviewMapper.convertToUserReviewForRestaurantRequest(oldUserReview);

       userReviewMapper.updateUserReview(oldUserReview, request);

       UserReview newUserReview = userReviewService.save(oldUserReview);

       UserReviewForRestaurantRequest newDTO = userReviewMapper.convertToUserReviewForRestaurantRequest(newUserReview);

       UpdateReviewForRestaurantDTO updateReviewForRestaurantDTO = userReviewMapper.convertToUpdateReviewForRestaurantDTO(oldDTO, newDTO);

       restaurantClient.updateReviewToRestaurant(updateReviewForRestaurantDTO);

       return userReviewMapper.convertToUserReviewResponse(newUserReview);
    }

    @Override
    public void deleteUserReview(Long id) {

        UserReview userReview = userReviewService.findById(id);

        User user = userService.findById(userReview.getUser().getId());

        userReview.getUser().setUsername(user.getUsername());

        UserReviewForRestaurantRequest userReviewForRestaurantRequest = userReviewMapper.convertToUserReviewForRestaurantRequest(userReview);

        restaurantClient.deleteReviewToRestaurant(userReviewForRestaurantRequest);

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
    public UserReviewResponse getUserReviewByUserUsername(String username) {

        UserReview userReview = userReviewService.findByUserUserName(username);

        return userReviewMapper.convertToUserReviewResponse(userReview);
    }
}
