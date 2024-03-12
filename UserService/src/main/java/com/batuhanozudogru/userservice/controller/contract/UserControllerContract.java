package com.batuhanozudogru.userservice.controller.contract;

import com.batuhanozudogru.userservice.dto.request.UserSaveRequest;
import com.batuhanozudogru.userservice.dto.response.UserResponse;

import java.util.List;

public interface UserControllerContract {

    List<UserResponse> getAllUsers();
    UserResponse saveUser(UserSaveRequest userSaveRequest);

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserSaveRequest userSaveRequest);

    void activeUser(Long id);

    void softDeleteUser(Long id);

    void hardDeleteUser(Long id);

    UserResponse getUserByTurkishRepublicIdNo(String turkishRepublicIdNo);

    UserResponse getUserByUsername(String username);




}
