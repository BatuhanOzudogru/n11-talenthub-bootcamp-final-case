package com.batuhanozudogru.userservice.mapper;

import com.batuhanozudogru.userservice.dto.request.UserSaveRequest;
import com.batuhanozudogru.userservice.dto.response.UserResponse;

import com.batuhanozudogru.userservice.entity.User;

import com.batuhanozudogru.userservice.entity.UserReview;
import com.batuhanozudogru.userservice.general.enums.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {

    public UserResponse convertToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        String firstName = null;
        String lastName = null;
        String turkishRepublicIdNumber = null;
        String username = null;
        LocalDate birthDate = null;
        BigDecimal latitude = null;
        BigDecimal longitude = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        firstName = user.getFirstName();
        lastName = user.getLastName();
        turkishRepublicIdNumber = user.getTurkishRepublicIdNumber();
        username = user.getUsername();
        birthDate = user.getBirthDate();
        latitude = user.getLatitude();
        longitude = user.getLongitude();
        createdAt = user.getCreatedAt();
        updatedAt = user.getUpdatedAt();

        List<UserReview> reviews = user.getReviewList();

        UserResponse userResponse = new UserResponse( firstName, lastName, turkishRepublicIdNumber, username, birthDate, latitude, longitude, createdAt, updatedAt, reviews );

        return userResponse;
    }

    public List<UserResponse> convertToUserResponseList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<>();

        for ( User user : users ) {
            list.add( convertToUserResponse( user ) );
        }

        return list;
    }

    public User convertToUser (UserSaveRequest request){
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( request.firstName() );
        user.setLastName( request.lastName() );
        user.setTurkishRepublicIdNumber(request.turkishRepublicIdNumber());
        user.setUsername( request.username() );
        user.setBirthDate( request.birthDate() );
        user.setLatitude( request.latitude() );
        user.setStatus(Status.ACTIVE);
        user.setLongitude( request.longitude() );


        return user;

    }

}
