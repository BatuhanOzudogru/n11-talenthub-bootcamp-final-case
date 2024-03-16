package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.controller.contract.UserReviewControllerContract;
import com.batuhanozudogru.userservice.dto.request.UserForReviewSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewUpdateRequest;
import com.batuhanozudogru.userservice.dto.request.UserSaveRequest;
import com.batuhanozudogru.userservice.dto.response.UserReviewResponse;
import com.batuhanozudogru.userservice.general.result.Result;
import com.batuhanozudogru.userservice.general.result.ResultData;
import com.batuhanozudogru.userservice.general.result.ResultHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-reviews")
@RequiredArgsConstructor
@Tag(name = "User Review Controller", description = "User Review Management")
public class UserReviewController {

    private final UserReviewControllerContract userReviewControllerContract;

    @GetMapping("/get-by-id/{id}")
    @Operation(
            description = "Get user review by id",
            summary = "Get User Review By Id"
    )
    public ResultData<UserReviewResponse> getUserReviewById(@PathVariable Long id) {
        return ResultHelper.success(userReviewControllerContract.getUserReviewById(id));
    }

    @GetMapping("/get-by-user-id/{userId}")
    @Operation(
            description = "Get user review list by user id",
            summary = "Get User Review List By User Id"
    )
    public ResultData<List<UserReviewResponse>> getUserReviewListByUserId(@PathVariable Long userId) {

        return ResultHelper.success(userReviewControllerContract.getUserReviewListByUserId(userId));
    }

    @GetMapping("/get-by-restaurant-id/{restaurantId}")
    @Operation(
            description = "Get user review list by restaurant id",
            summary = "Get User Review List By Restaurant Id"
    )
    public ResultData<List<UserReviewResponse>> getUserReviewByRestaurantId(@PathVariable String restaurantId) {

        return ResultHelper.success(userReviewControllerContract.getUserReviewByRestaurantId(restaurantId));
    }

    @GetMapping("/get-by-username/{username}")
    @Operation(
            description = "Get user review list by user username",
            summary = "Get User Review List By User Username"
    )
    public ResultData<UserReviewResponse> getUserReviewByUserUsername(@PathVariable String username) {

        return ResultHelper.success(userReviewControllerContract.getUserReviewByUserUsername(username));
    }

    @PostMapping("/save")
    @Operation(
            description = "Creates a new user review",
            summary = "Save User Review",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Review Save Request",
                    content ={
                            @Content(
                                    mediaType = "application/json",
                                    schema =@Schema(
                                            implementation = UserReviewSaveRequest.class

                                    ),
                                    examples={
                                            @ExampleObject(
                                                    name = "User Review Save Request",
                                                    summary = "You have to send a request like this",
                                                    value = "{\n" +
                                                            "  \"review\": \"This is a good restaurant\",\n" +
                                                            "  \"restaurantId\": \"1234567890\",\n" +
                                                            "  \"rate\": \"FIVE\",\n" +
                                                            "  \"user\": {\n" +
                                                            "    \"id\": 1\n" +
                                                            "  }\n" +
                                                            "}",
                                                    description = "This is an example of a user review save request. Ensure that the user id is correctly provided and the rate is one of the following: ONE, TWO, THREE, FOUR, FIVE."
                                            )
                                    }
                            )
                    }
            )
    )
    public ResultData<UserReviewResponse> saveUserReview(@RequestBody UserReviewSaveRequest userReviewSaveRequest) {

        return ResultHelper.created(userReviewControllerContract.saveUserReview(userReviewSaveRequest));
    }

    @PutMapping("/update/{id}")
    @Operation(
            description = "Updates an existing user review",
            summary = "Update User Review",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Review Update Request",
                    content ={
                            @Content(
                                    mediaType = "application/json",
                                    schema =@Schema(
                                            implementation = UserReviewUpdateRequest.class
                                    ),
                                    examples={
                                            @ExampleObject(
                                                    name = "User Review Update Request",
                                                    summary = "You have to send a request like this",
                                                    value = "{\n" +
                                                            "  \"review\": \"This is a good restaurant\",\n" +
                                                            "  \"rate\": \"FIVE\"\n" +
                                                            "}",
                                                    description = "This is an example of a user review update request. Ensure that the rate is one of the following: ONE, TWO, THREE, FOUR, FIVE."
                                            )
                                    }
                            )
                    }
            )
    )
    public ResultData<UserReviewResponse> updateUserReview(@PathVariable Long id, @RequestBody UserReviewUpdateRequest request) {

        return ResultHelper.updated(userReviewControllerContract.updateUserReview(id, request));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Hard Delete User Review By Id", description = "Deletes the user review permanently.")
    public Result deleteUserReview(@PathVariable Long id) {

        userReviewControllerContract.deleteUserReview(id);

        return ResultHelper.hardDeleted();
    }

    @GetMapping
    @Operation(
            description = "Get all user reviews",
            summary = "Get All User Reviews"
    )
    public ResultData<List<UserReviewResponse>> getUserReviews() {

        return ResultHelper.success(userReviewControllerContract.getAllUserReviews());
    }



}
