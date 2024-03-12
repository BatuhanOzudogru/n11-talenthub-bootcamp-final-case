package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.controller.contract.UserReviewControllerContract;
import com.batuhanozudogru.userservice.controller.contract.impl.UserReviewControllerContractImpl;
import com.batuhanozudogru.userservice.dto.request.UserReviewSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewUpdateRequest;
import com.batuhanozudogru.userservice.dto.response.UserReviewResponse;
import com.batuhanozudogru.userservice.entity.UserReview;
import com.batuhanozudogru.userservice.mapper.UserReviewMapper;
import com.batuhanozudogru.userservice.service.UserReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-reviews")
@RequiredArgsConstructor
public class UserReviewController {

    private final UserReviewControllerContract userReviewControllerContract;

    @GetMapping("/getById/{id}")
    public UserReviewResponse getUserReviewById(@PathVariable Long id) {
        return userReviewControllerContract.getUserReviewById(id);
    }

    @GetMapping("/get-by-user-id/{userId}")
    public List<UserReviewResponse> getUserReviewListByUserId(@PathVariable Long userId) {
        return userReviewControllerContract.getUserReviewListByUserId(userId);
    }

    @GetMapping("/get-by-restaurant-id/{restaurantId}")
    public List<UserReviewResponse> getUserReviewByRestaurantId(@PathVariable String restaurantId) {
        return userReviewControllerContract.getUserReviewByRestaurantId(restaurantId);
    }

    @GetMapping("/get-by-username/{username}")
    public UserReviewResponse getUserReviewByUserUsername(@PathVariable String username) {
        return userReviewControllerContract.getUserReviewByUserUsername(username);
    }

    @PostMapping("/save")
    public UserReviewResponse saveUserReview(@RequestBody UserReviewSaveRequest userReviewSaveRequest) {
        return userReviewControllerContract.saveUserReview(userReviewSaveRequest);
    }

    @PutMapping("/update/{id}")
    public UserReviewResponse updateUserReview(@PathVariable Long id, @RequestBody UserReviewUpdateRequest request) {
        return userReviewControllerContract.updateUserReview(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserReview(@PathVariable Long id) {
        userReviewControllerContract.deleteUserReview(id);
    }

    @GetMapping
    public List<UserReviewResponse> getUserReviews() {
        return userReviewControllerContract.getAllUserReviews();
    }

//    @GetMapping
//    public List<UserReviewResponse> getUserReviews() {
//        List<UserReview> userReviews = userReviewService.getAllUserReviews();
//        return userReviewMapper.convertToUserReviewResponseList(userReviews);
//
//    }
//
//    @PostMapping("/save")
//    public UserReviewResponse saveUserReview(@RequestBody UserReviewSaveRequest userReviewSaveRequest) {
//        return userReviewService.saveUserReview(userReviewSaveRequest);
//    }


}
