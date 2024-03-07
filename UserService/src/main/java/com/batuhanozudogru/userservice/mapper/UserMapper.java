package com.batuhanozudogru.userservice.mapper;

import com.batuhanozudogru.userservice.dto.request.UserSaveRequest;
import com.batuhanozudogru.userservice.dto.response.UserResponse;
import com.batuhanozudogru.userservice.dto.response.UserUserReviewResponse;
import com.batuhanozudogru.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE ,componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "status", constant = "ACTIVE")
    User converToUser(UserSaveRequest userSaveRequest);

    UserResponse convertToUserResponse(User user);

    List<UserResponse> convertToUserResponseList(List<User> users);

    @Mapping(target="id",ignore = true)
    void updateUser(@MappingTarget User user, UserSaveRequest userSaveRequest);
}
