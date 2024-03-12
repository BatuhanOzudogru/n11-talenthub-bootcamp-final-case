package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.controller.contract.UserControllerContract;
import com.batuhanozudogru.userservice.controller.contract.impl.UserControllerContractImpl;
import com.batuhanozudogru.userservice.dto.request.UserSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserUpdateRequest;
import com.batuhanozudogru.userservice.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserControllerContract userControllerContract;

    @PostMapping("/save")
    public UserResponse saveUser(UserSaveRequest userSaveRequest) {

        return userControllerContract.saveUser(userSaveRequest);

    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userControllerContract.getAllUsers();
    }

    @GetMapping("/get-by-id/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userControllerContract.getUserById(id);
    }

    @GetMapping("/get-by-turkish-republic-id/{turkishRepublicIdNo}")
    public UserResponse getUserByTurkishRepublicIdNo(@PathVariable String turkishRepublicIdNo) {
        return userControllerContract.getUserByTurkishRepublicIdNo(turkishRepublicIdNo);
    }

    @GetMapping("/get-by-username/{username}")
    public UserResponse getUserByUsername(@PathVariable String username) {
        return userControllerContract.getUserByUsername(username);
    }

    @DeleteMapping("/hard-delete-by-id/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userControllerContract.hardDeleteUser(id);
    }

    @DeleteMapping("/soft-delete-by-id/{id}")
    public void softDeleteUserById(@PathVariable Long id) {
        userControllerContract.softDeleteUser(id);
    }

    @PatchMapping("/active-user-by-id/{id}")
    public void activeUserById(@PathVariable Long id) {
        userControllerContract.activeUser(id);
    }

    @PutMapping("/update-user-by-id/{id}")
    public UserResponse updateUserById(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        return userControllerContract.updateUser(id, request);
    }



}
