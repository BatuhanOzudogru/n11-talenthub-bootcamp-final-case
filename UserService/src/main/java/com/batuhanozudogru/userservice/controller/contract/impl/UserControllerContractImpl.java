package com.batuhanozudogru.userservice.controller.contract.impl;

import com.batuhanozudogru.userservice.controller.contract.UserControllerContract;
import com.batuhanozudogru.userservice.dto.request.UserSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserUpdateRequest;
import com.batuhanozudogru.userservice.dto.response.UserResponse;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.mapper.UserMapper;
import com.batuhanozudogru.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserControllerContractImpl implements UserControllerContract {

    private final UserService userService;
    private final UserMapper userMapper;
    @Override
    public List<UserResponse> getAllUsers() {

        List<User> userList =  userService.findAllActiveUsers();

        return userMapper.convertToUserResponseList(userList);
    }

    @Override
    public UserResponse saveUser(UserSaveRequest userSaveRequest) {

        User user = userMapper.convertToUser(userSaveRequest);

        return userMapper.convertToUserResponse(userService.save(user));
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = userService.findById(id);
        return userMapper.convertToUserResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest request) {

        User user = userService.findById(id);
        userMapper.updateUser(user, request);
        return userMapper.convertToUserResponse(userService.update(user));
    }

    @Override
    public void activeUser(Long id) {
        userService.activeUser(id);
    }

    @Override
    public void softDeleteUser(Long id) {
        userService.passiveUser(id);
    }

    @Override
    public void hardDeleteUser(Long id) {
        userService.deleteById(id);
    }






    @Override
    public UserResponse getUserByTurkishRepublicIdNo(String turkishRepublicIdNo) {

        User user = userService.findByTurkishRepublicIdNo(turkishRepublicIdNo);

        return userMapper.convertToUserResponse(user);
    }

    @Override
    public UserResponse getUserByUsername(String username) {

        User user = userService.findByUsername(username);

        return userMapper.convertToUserResponse(user);
    }
}
