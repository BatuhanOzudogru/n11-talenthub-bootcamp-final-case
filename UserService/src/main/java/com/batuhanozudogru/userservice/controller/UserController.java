package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.controller.contract.UserControllerContract;
import com.batuhanozudogru.userservice.dto.request.UserSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserUpdateRequest;
import com.batuhanozudogru.userservice.dto.response.UserResponse;
import com.batuhanozudogru.userservice.general.result.Result;
import com.batuhanozudogru.userservice.general.result.ResultData;
import com.batuhanozudogru.userservice.general.result.ResultHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserControllerContract userControllerContract;

    @PostMapping("/save")
    public ResultData<UserResponse> saveUser(@RequestBody UserSaveRequest userSaveRequest) {

        return ResultHelper.created(userControllerContract.saveUser(userSaveRequest));

    }

    @GetMapping
    public ResultData<List<UserResponse>> getUsers() {

        return ResultHelper.success(userControllerContract.getAllUsers());
    }

    @GetMapping("/get-by-id/{id}")
    public ResultData<UserResponse> getUserById(@PathVariable Long id) {

        return ResultHelper.success(userControllerContract.getUserById(id));
    }

    @GetMapping("/get-by-turkish-republic-id/{turkishRepublicIdNo}")
    public ResultData<UserResponse> getUserByTurkishRepublicIdNo(@PathVariable String turkishRepublicIdNo) {

        return ResultHelper.success(userControllerContract.getUserByTurkishRepublicIdNo(turkishRepublicIdNo));
    }

    @GetMapping("/get-by-username/{username}")
    public ResultData<UserResponse> getUserByUsername(@PathVariable String username) {

        return ResultHelper.success(userControllerContract.getUserByUsername(username));
    }

    @DeleteMapping("/hard-delete-by-id/{id}")
    public Result deleteUserById(@PathVariable Long id) {

        userControllerContract.hardDeleteUser(id);

        return ResultHelper.hardDeleted();
    }

    @DeleteMapping("/soft-delete-by-id/{id}")
    public Result softDeleteUserById(@PathVariable Long id) {

        userControllerContract.softDeleteUser(id);

        return ResultHelper.softDeleted();
    }

    @PatchMapping("/active-user-by-id/{id}")
    public Result activeUserById(@PathVariable Long id) {

        userControllerContract.activeUser(id);

        return ResultHelper.activated();
    }

    @PutMapping("/update-user-by-id/{id}")
    public ResultData<UserResponse> updateUserById(@PathVariable Long id, @RequestBody UserUpdateRequest request) {

        return ResultHelper.updated(userControllerContract.updateUser(id, request));
    }



}
