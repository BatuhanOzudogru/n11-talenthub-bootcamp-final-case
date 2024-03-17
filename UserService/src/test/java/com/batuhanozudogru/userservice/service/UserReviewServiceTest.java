package com.batuhanozudogru.userservice.service;

import com.batuhanozudogru.userservice.client.RestaurantClient;
import com.batuhanozudogru.userservice.dao.UserRepository;
import com.batuhanozudogru.userservice.dao.UserReviewRepository;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.entity.UserReview;
import com.batuhanozudogru.userservice.general.enums.ReviewRate;
import com.batuhanozudogru.userservice.general.exception.NullFieldException;
import com.batuhanozudogru.userservice.general.exception.RestaurantNotFoundException;
import com.batuhanozudogru.userservice.general.exception.UserNotFoundException;
import com.batuhanozudogru.userservice.general.exception.UserReviewNotFoundException;
import com.batuhanozudogru.userservice.general.result.ResultData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserReviewServiceTest {

    @Mock
    private UserReviewRepository userReviewRepository;

    @Mock
    private RestaurantClient restaurantClient;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserReviewService userReviewService;

    @Test
    void shouldNotSaveUserReviewWhenUserIsNull() {
        //given
        UserReview userReview = new UserReview();
        userReview.setReview("Good");
        userReview.setRate(ReviewRate.FIVE);
        userReview.setRestaurantId("qwe");
        userReview.setCreatedAt(LocalDateTime.now());
        userReview.setUpdatedAt(LocalDateTime.now());

        //when
       // Mockito.when(userReviewRepository.save(userReview)).thenReturn(userReview);


        //then
        assertThrows(NullFieldException.class, () -> userReviewService.save(userReview));
    }

    @Test
    void shouldSaveUserReview() {
        //given
        UserReview userReview = new UserReview();
        userReview.setReview("Good");
        userReview.setRate(ReviewRate.FIVE);
        userReview.setRestaurantId("test");
        userReview.setUser(new User());
        userReview.setCreatedAt(LocalDateTime.now());
        userReview.setUpdatedAt(LocalDateTime.now());

        //when
        Mockito.when(userReviewRepository.save(userReview)).thenReturn(userReview);



        //then
        assertEquals(userReview, userReviewService.save(userReview));
    }

    @Test
    void shouldFindAllUserReviews() {
        //given
        UserReview userReview = new UserReview();
        userReview.setReview("Good");
        userReview.setRate(ReviewRate.FIVE);
        userReview.setRestaurantId("qwe");
        userReview.setUser(new User());
        userReview.setCreatedAt(LocalDateTime.now());
        userReview.setUpdatedAt(LocalDateTime.now());

        UserReview userReview2 = new UserReview();
        userReview2.setReview("Good");
        userReview2.setRate(ReviewRate.FIVE);
        userReview2.setRestaurantId("qwe");
        userReview2.setUser(new User());
        userReview2.setCreatedAt(LocalDateTime.now());
        userReview2.setUpdatedAt(LocalDateTime.now());

        List<UserReview> userReviews = List.of(userReview, userReview2);

        //when
        Mockito.when(userReviewRepository.findAll()).thenReturn(userReviews);

        List<UserReview> result = userReviewService.getAllUserReviews();

        //then
        assertEquals(userReviews.size(), result.size());
    }

    @Test
    void shouldThrowExceptionWhenUserReviewIsNull() {
        assertThrows(NullFieldException.class, () -> userReviewService.save(null));
    }

    @Test
    void shouldThrowExceptionWhenInvalidRestaurantId() {
        //given
        UserReview userReview = new UserReview();
        userReview.setReview("Good");
        userReview.setRate(ReviewRate.FIVE);
        userReview.setRestaurantId("invalid");
        userReview.setUser(new User());
        userReview.setCreatedAt(LocalDateTime.now());
        userReview.setUpdatedAt(LocalDateTime.now());

        //when
        Mockito.when(restaurantClient.getRestaurantById("invalid")).thenReturn(new ResultData<>(false, "404", "Restaurant not found",null));

        //then
        assertThrows(RestaurantNotFoundException.class, () -> userReviewService.save(userReview));
    }
    @Test
    void shouldNotFindUserReviewWhenReviewIdIsWrong() {
        //given
        Long id = 1L;

        //when
        Mockito.when(userReviewRepository.findById(id)).thenReturn(Optional.empty());

        //then
        assertThrows(UserReviewNotFoundException.class, () -> userReviewService.findById(id));
    }


}