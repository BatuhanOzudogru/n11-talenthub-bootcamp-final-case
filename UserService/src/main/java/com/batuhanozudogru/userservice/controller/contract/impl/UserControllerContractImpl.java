package com.batuhanozudogru.userservice.controller.contract.impl;

import com.batuhanozudogru.userservice.controller.contract.UserControllerContract;
import com.batuhanozudogru.userservice.dto.request.UserSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserUpdateRequest;
import com.batuhanozudogru.userservice.dto.response.UserResponse;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.mapper.UserMapper;
import com.batuhanozudogru.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserControllerContractImpl implements UserControllerContract {

    private final UserService userService;
    @Override
    public List<UserResponse> getAllUsers() {

        log.info("Request received to get all users.");

        List<User> userList =  userService.findAllActiveUsers();

        List<UserResponse> userResponseList = UserMapper.convertToUserResponseList(userList);
        return userResponseList;
    }

    @Override
    public List<UserResponse> getAllPassiveUsers() {

        log.info("Request received to get all passive users.");

        List<User> userList =  userService.findAllPassiveUsers();

        List<UserResponse> userResponseList = UserMapper.convertToUserResponseList(userList);

        return userResponseList;
    }

    @Override

    public UserResponse saveUser(UserSaveRequest userSaveRequest) {

        log.info("Request received to save user: {}", userSaveRequest.toString());

        User user = UserMapper.convertToUser(userSaveRequest);

        return UserMapper.convertToUserResponse(userService.save(user));
    }

    @Override
    public UserResponse getUserById(Long id) {

        log.info("Request received to get user by ID: {}", id);

        User user = userService.findById(id);

        return UserMapper.convertToUserResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest request) {

        log.info("Request received to update user with ID: {}", id);

        User user = userService.findById(id);

        UserMapper.updateUser(user, request);

        userService.save(user);

        return UserMapper.convertToUserResponse(user);
    }

    @Override
    public void activeUser(Long id) {

        log.info("Request received to activate user with ID: {}", id);

        userService.activeUser(id);
    }

    @Override
    public void softDeleteUser(Long id) {

        log.info("Request received to soft delete user with ID: {}", id);

        userService.passiveUser(id);
    }

    @Override
    public void hardDeleteUser(Long id) {

        log.info("Request received to hard delete user with ID: {}", id);

        userService.deleteById(id);
    }






    @Override
    public UserResponse getUserByTurkishRepublicIdNo(String turkishRepublicIdNo) {

        log.info("Request received to get user by Turkish Republic ID number: {}", turkishRepublicIdNo);

        User user = userService.findByTurkishRepublicIdNo(turkishRepublicIdNo);

        return UserMapper.convertToUserResponse(user);
    }

    @Override
    public UserResponse getUserByUsername(String username) {

        log.info("Request received to get user by username: {}", username);

        User user = userService.findByUsername(username);

        return UserMapper.convertToUserResponse(user);
    }
}
