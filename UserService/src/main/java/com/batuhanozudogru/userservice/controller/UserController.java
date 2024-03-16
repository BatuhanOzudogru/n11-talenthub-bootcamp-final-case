package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.controller.contract.UserControllerContract;
import com.batuhanozudogru.userservice.dto.request.UserSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserUpdateRequest;
import com.batuhanozudogru.userservice.dto.response.UserResponse;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "User Management")
public class UserController {

    private final UserControllerContract userControllerContract;

    @PostMapping("/save")
    @Operation(
            description = "Creates a new user",
            summary = "Save User",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Save Request",
                    content ={
                         @Content(
                                    mediaType = "application/json",
                                    schema =@Schema(
                                            implementation = UserSaveRequest.class
                                    ),
                            examples={
                                        @ExampleObject(
                                                name="Example User Save Request",
                                                summary = "You have to send a request like this",
                                                value = "{\n" +
                                                        "  \"firstName\": \"Batuhan\",\n" +
                                                        "  \"lastName\": \"Ozudogru\",\n" +
                                                        "  \"turkishRepublicIdNumber\": \"12345678901\",\n" +
                                                        "  \"username\": \"batuhanozudogru\",\n" +
                                                        "  \"birthDate\": \"1996-07-01\",\n" +
                                                        "  \"latitude\": 41.0082,\n" +
                                                        "  \"longitude\": 28.9784\n" +
                                                        "}",
                                                description = "This is an example of a user save request. Ensure that the Turkish Republic ID Number (TC) is correctly provided."
                                        )
                            }
                         )
                    }
            )
    )
    public ResultData<UserResponse> saveUser(@RequestBody UserSaveRequest userSaveRequest) {

        return ResultHelper.created(userControllerContract.saveUser(userSaveRequest));

    }

    @GetMapping
    @Operation(summary = "Get All Users", description = "Retrieves all active customers")
    public ResultData<List<UserResponse>> getUsers() {

        return ResultHelper.success(userControllerContract.getAllUsers());
    }

    @GetMapping("/get-by-id/{id}")
    @Operation(summary = "Get User By Id", description = "Retrieves a user by id")
    public ResultData<UserResponse> getUserById(@PathVariable Long id) {

        return ResultHelper.success(userControllerContract.getUserById(id));
    }

    @GetMapping("/get-by-turkish-republic-id/{turkishRepublicIdNo}")
    @Operation(summary = "Get User By Turkish Republic Id No", description = "Retrieves a user by turkish republic id no")
    public ResultData<UserResponse> getUserByTurkishRepublicIdNo(@PathVariable String turkishRepublicIdNo) {

        return ResultHelper.success(userControllerContract.getUserByTurkishRepublicIdNo(turkishRepublicIdNo));
    }

    @GetMapping("/get-by-username/{username}")
    @Operation(summary = "Get User By Username", description = "Retrieves a user by username")
    public ResultData<UserResponse> getUserByUsername(@PathVariable String username) {

        return ResultHelper.success(userControllerContract.getUserByUsername(username));
    }

    @DeleteMapping("/hard-delete-by-id/{id}")
    @Operation(summary = "Hard Delete User By Id", description = "Deletes a user permanently.")
    public Result deleteUserById(@PathVariable Long id) {

        userControllerContract.hardDeleteUser(id);

        return ResultHelper.hardDeleted();
    }

    @DeleteMapping("/soft-delete-by-id/{id}")
    @Operation(summary = "Soft Delete User By Id", description = "Sets the user's status to passive.")
    public Result softDeleteUserById(@PathVariable Long id) {

        userControllerContract.softDeleteUser(id);

        return ResultHelper.softDeleted();
    }

    @PatchMapping("/active-user-by-id/{id}")
    @Operation(summary = "Active User By Id", description = "Sets the user's status to active.")
    public Result activeUserById(@PathVariable Long id) {

        userControllerContract.activeUser(id);

        return ResultHelper.activated();
    }

    @PutMapping("/update-user-by-id/{id}")
    @Operation(
            description = "Updates a new user",
            summary = "Update User",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Update Request",
                    content ={
                            @Content(
                                    mediaType = "application/json",
                                    schema =@Schema(
                                            implementation = UserUpdateRequest.class
                                    ),
                                    examples={
                                            @ExampleObject(
                                                    name="Example User Update Request",
                                                    summary = "You have to send a request like this",
                                                    value = "{\n" +
                                                            "  \"username\": \"batuhanozudogru\",\n" +
                                                            "  \"latitude\": 41.0082,\n" +
                                                            "  \"longitude\": 28.9784\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    public ResultData<UserResponse> updateUserById(@PathVariable Long id, @RequestBody UserUpdateRequest request) {

        return ResultHelper.updated(userControllerContract.updateUser(id, request));
    }



}
