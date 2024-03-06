package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.controller.contract.impl.UserControllerContractImpl;
import com.batuhanozudogru.userservice.dto.request.UserSaveRequest;
import com.batuhanozudogru.userservice.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserControllerContractImpl userControllerContract;

    @PostMapping("/save")
    public UserResponse saveUser(UserSaveRequest userSaveRequest) {

        return userControllerContract.saveUser(userSaveRequest);

    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userControllerContract.getAllUsers();
    }

    @GetMapping("/getById/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userControllerContract.getUserById(id);
    }

    @GetMapping("/getByTurkishRepublicIdNo/{turkishRepublicIdNo}")
    public UserResponse getUserByTurkishRepublicIdNo(@PathVariable String turkishRepublicIdNo) {
        return userControllerContract.getUserByTurkishRepublicIdNo(turkishRepublicIdNo);
    }

    @GetMapping("/getByUsername/{username}")
    public UserResponse getUserByUsername(@PathVariable String username) {
        return userControllerContract.getUserByUsername(username);
    }



}
