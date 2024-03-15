package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.controller.contract.UserReviewControllerContract;
import com.batuhanozudogru.userservice.dto.request.UserReviewSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewUpdateRequest;
import com.batuhanozudogru.userservice.dto.response.UserReviewResponse;
import com.batuhanozudogru.userservice.general.result.Result;
import com.batuhanozudogru.userservice.general.result.ResultData;
import com.batuhanozudogru.userservice.general.result.ResultHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-reviews")
@RequiredArgsConstructor
public class UserReviewController {

    private final UserReviewControllerContract userReviewControllerContract;

    @GetMapping("/get-by-id/{id}")
    public ResultData<UserReviewResponse> getUserReviewById(@PathVariable Long id) {
        return ResultHelper.success(userReviewControllerContract.getUserReviewById(id));
    }

    @GetMapping("/get-by-user-id/{userId}")
    public ResultData<List<UserReviewResponse>> getUserReviewListByUserId(@PathVariable Long userId) {

        return ResultHelper.success(userReviewControllerContract.getUserReviewListByUserId(userId));
    }

    @GetMapping("/get-by-restaurant-id/{restaurantId}")
    public ResultData<List<UserReviewResponse>> getUserReviewByRestaurantId(@PathVariable String restaurantId) {

        return ResultHelper.success(userReviewControllerContract.getUserReviewByRestaurantId(restaurantId));
    }

    @GetMapping("/get-by-username/{username}")
    public ResultData<UserReviewResponse> getUserReviewByUserUsername(@PathVariable String username) {

        return ResultHelper.success(userReviewControllerContract.getUserReviewByUserUsername(username));
    }

    @PostMapping("/save")
    public ResultData<UserReviewResponse> saveUserReview(@RequestBody UserReviewSaveRequest userReviewSaveRequest) {

        return ResultHelper.created(userReviewControllerContract.saveUserReview(userReviewSaveRequest));
    }

    @PutMapping("/update/{id}")
    public ResultData<UserReviewResponse> updateUserReview(@PathVariable Long id, @RequestBody UserReviewUpdateRequest request) {

        return ResultHelper.updated(userReviewControllerContract.updateUserReview(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteUserReview(@PathVariable Long id) {

        userReviewControllerContract.deleteUserReview(id);

        return ResultHelper.hardDeleted();
    }

    @GetMapping
    public ResultData<List<UserReviewResponse>> getUserReviews() {

        return ResultHelper.success(userReviewControllerContract.getAllUserReviews());
    }



}
