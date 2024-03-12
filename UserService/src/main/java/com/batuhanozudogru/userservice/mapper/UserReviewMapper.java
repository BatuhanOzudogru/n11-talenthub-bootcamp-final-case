package com.batuhanozudogru.userservice.mapper;


import com.batuhanozudogru.userservice.dto.request.UserReviewSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewUpdateRequest;
import com.batuhanozudogru.userservice.dto.response.UserReviewResponse;
import com.batuhanozudogru.userservice.entity.UserReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;


import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE ,componentModel = "spring")
public interface UserReviewMapper {

    @Mapping(target = "user", source = "user")
    UserReview convertToUserReview(UserReviewSaveRequest userReviewSaveRequest);

    @Mapping(target = "username", source = "user.username")
    UserReviewResponse convertToUserReviewResponse(UserReview userReview);

    List<UserReviewResponse> convertToUserReviewResponseList(List<UserReview> userReviews);

    @Mapping(target="id",ignore = true)
    void updateUserReview(@MappingTarget UserReview userReview, UserReviewUpdateRequest request);



}
