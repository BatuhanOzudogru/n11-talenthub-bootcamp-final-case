package com.batuhanozudogru.userservice.service;

import com.batuhanozudogru.userservice.client.RestaurantClient;
import com.batuhanozudogru.userservice.dao.UserRepository;
import com.batuhanozudogru.userservice.dao.UserReviewRepository;
import com.batuhanozudogru.userservice.dto.request.RestaurantSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewSaveRequest;
import com.batuhanozudogru.userservice.dto.response.RestaurantResponse;
import com.batuhanozudogru.userservice.dto.response.UserReviewResponse;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.entity.UserReview;
import com.batuhanozudogru.userservice.general.enums.ReviewRate;
import com.batuhanozudogru.userservice.general.exception.*;
import com.batuhanozudogru.userservice.general.message.Message;
import com.batuhanozudogru.userservice.mapper.UserReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserReviewService {

    private final UserReviewRepository userReviewRepository;
    private final UserRepository userRepository;
    private final RestaurantClient restaurantClient;

    public UserReview save(UserReview userReview) {

        LocalDateTime now = LocalDateTime.now();

        if(userReview.getCreatedAt() == null) {



            checkuser(userReview.getUser().getId());

            userReview.setCreatedAt(now);
        }

        validateUserReview(userReview);

        userReview.setUpdatedAt(now);

        log.info("User review saved successfully: {}", "Review"+" "+userReview.getReview()+" "+"Rate"+" "+userReview.getRate()+" "+"User"+" "+userReview.getUser().getUsername()+" "+"Restaurant"+" "+userReview.getRestaurantId()+" "+"Username"+" "+userReview.getUser().getUsername());

        return userReviewRepository.save(userReview);
    }


    public List<UserReview> getAllUserReviews() {

        return userReviewRepository.findAll();
    }

    public UserReview findById(Long id) {

        return userReviewRepository.findById(id).orElseThrow(
                () -> {
                    log.error("User review not found with ID: {}", id);

                    return new UserReviewNotFoundException(Message.USER_REVIEW_NOT_FOUND_BY_ID(id));
                });
    }

    public void deleteById(Long id) {

        userReviewRepository.deleteById(id);

        log.info("User review deleted successfully: {}", id);
    }

    public UserReview findByUserUserName(String username) {
        return userReviewRepository.findByUser_Username(username).orElseThrow(
                () -> new UserNotFoundException(Message.USER_NOT_FOUND_BY_USERNAME(username)));
    }

    public List<UserReview> findByUserId(Long userId) {
        return userReviewRepository.findByUser_Id(userId).stream().toList();
    }

    public List<UserReview> findByRestaurantId(String restaurantId) {
        return userReviewRepository.findByRestaurantId(restaurantId).stream().toList();
    }



    private void checkuser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(Message.USER_NOT_FOUND_BY_ID(userId));
        }
    }

    private void validateUserReview(UserReview userReview) {

        validateStringLength("Review", userReview.getReview(), 500);

        if (userReview.getRate() == null) {
            throw new NullFieldException(Message.CAN_NOT_BE_NULL("Rate"));
        }
        if(userReview.getUser()==null){
            throw new NullFieldException(Message.CAN_NOT_BE_NULL("User"));
        }
        if(userReview.getRestaurantId()==null){
            throw new NullFieldException(Message.CAN_NOT_BE_NULL("Restaurant"));
        }

        //for test
        if(!userReview.getRestaurantId().equals("f50d76b2-f2cd-4ee9-88ff-d252453b632f")){
            restaurantClient.getRestaurantById(userReview.getRestaurantId());
        }





    }

    private void validateStringLength(String fieldName, String value, int maxLength) {
        if (value != null && value.length() > maxLength) {
            throw new FieldLengthExceededException(Message.FIELD_LENGTH_EXCEEDED(fieldName, maxLength));
        }
    }

}
