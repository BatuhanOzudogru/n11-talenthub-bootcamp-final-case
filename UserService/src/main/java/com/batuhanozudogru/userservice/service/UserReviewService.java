package com.batuhanozudogru.userservice.service;

import com.batuhanozudogru.userservice.dao.UserRepository;
import com.batuhanozudogru.userservice.dao.UserReviewRepository;
import com.batuhanozudogru.userservice.dto.request.UserReviewSaveRequest;
import com.batuhanozudogru.userservice.dto.response.UserReviewResponse;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.entity.UserReview;
import com.batuhanozudogru.userservice.general.exception.*;
import com.batuhanozudogru.userservice.general.message.Message;
import com.batuhanozudogru.userservice.mapper.UserReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserReviewService {

    private final UserReviewRepository userReviewRepository;
    private final UserRepository userRepository;
    private final UserReviewMapper userReviewMapper;

    public UserReview save(UserReview userReview) {

        LocalDateTime now = LocalDateTime.now();

        if(userReview.getCreatedAt() == null) {

            validateUserReview(userReview);

            checkuser(userReview.getUser().getId());

            userReview.setCreatedAt(now);
        }

        userReview.setUpdatedAt(now);

        return userReviewRepository.save(userReview);
    }


    public List<UserReview> getAllUserReviews() {
        return userReviewRepository.findAll();
    }

    public UserReview findById(Long id) {

        return userReviewRepository.findById(id).orElseThrow(
                () -> new UserReviewNotFoundException(Message.USER_REVIEW_NOT_FOUND_BY_ID(id)));

    }

    public void deleteById(Long id) {
        userReviewRepository.deleteById(id);
    }

    public List<UserReview> findByUserUserName(String userName) {
        return userReviewRepository.findByUser_Username(userName).stream().toList();
    }

    public List<UserReview> findByUserId(Long userId) {
        return userReviewRepository.findByUser_Id(userId).stream().toList();
    }

    public List<UserReview> findByRestaurantId(Long restaurantId) {
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


    }

    private void validateStringLength(String fieldName, String value, int maxLength) {
        if (value != null && value.length() > maxLength) {
            throw new FieldLengthExceededException(Message.FIELD_LENGTH_EXCEEDED(fieldName, maxLength));
        }
    }

}
