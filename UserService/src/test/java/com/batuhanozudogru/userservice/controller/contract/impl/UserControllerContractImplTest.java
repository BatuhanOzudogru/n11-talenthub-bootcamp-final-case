package com.batuhanozudogru.userservice.controller.contract.impl;

import com.batuhanozudogru.userservice.dto.request.UserSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserUpdateRequest;
import com.batuhanozudogru.userservice.dto.response.UserResponse;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.general.enums.Status;
import com.batuhanozudogru.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserControllerContractImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserControllerContractImpl userControllerContractImpl;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;


    @Test
    void shouldGetAllUsers() {

        //given
        User user1 = new User();
        user1.setStatus(Status.ACTIVE);
        user1.setFirstName("Batuhan");
        user1.setLastName("Ozudogru");

        User user2 = new User();
        user2.setStatus(Status.ACTIVE);
        user2.setFirstName("John");
        user2.setLastName("Doe");

        User user3 = new User();
        user3.setStatus(Status.ACTIVE);
        user3.setFirstName("Jane");
        user3.setLastName("Doe");

        User user4 = new User();
        user4.setStatus(Status.ACTIVE);
        user4.setFirstName("userFirstName4");
        user4.setLastName("userLastName4");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        //when
        Mockito.when(userService.findAllActiveUsers()).thenReturn(users);

        List<UserResponse> results = userControllerContractImpl.getAllUsers();

        //then
        assertEquals(users.size(), results.size());

        for (int i=0; i<results.size(); i++) {
            UserResponse result = results.get(i);
            User eachUser = users.get(i);
            assertEquals(eachUser.getFirstName(), result.firstName());
            assertEquals(eachUser.getLastName(), result.lastName());
        }
    }


    @Test
    void shouldGetUserById() {

        //given
        Long id = 1L;
        User user1 = new User();
        user1.setId(id);
        user1.setStatus(Status.ACTIVE);
        user1.setFirstName("Batuhan");
        user1.setLastName("Ozudogru");

        //when
        Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(user1);

        UserResponse userResponse = userControllerContractImpl.getUserById(id);

        //then

        assertEquals(user1.getFirstName(), userResponse.firstName());
        assertEquals(user1.getLastName(), userResponse.lastName());
    }

    @Test
    void shouldUpdateUser() {

        //given
        Long id = 1L;
        User user1 = new User();
        user1.setId(id);
        user1.setStatus(Status.ACTIVE);
        user1.setUsername("batuhanozudogru");
        user1.setLatitude(BigDecimal.valueOf(0.50));
        user1.setLongitude(BigDecimal.valueOf(0.40));

        UserUpdateRequest userUpdateRequest = new UserUpdateRequest("username",
                                                                    BigDecimal.valueOf(50.40),
                                                                    BigDecimal.valueOf(40.50));

        //when
        Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(user1);

        UserResponse result = userControllerContractImpl.updateUser(id,userUpdateRequest);

        //then
        InOrder inOrder = Mockito.inOrder(userService);
        inOrder.verify(userService).findById(id);
        inOrder.verify(userService).save(userArgumentCaptor.capture());
        inOrder.verifyNoMoreInteractions();

        User userArgumentCaptorValue = userArgumentCaptor.getValue();
        assertEquals(id, userArgumentCaptorValue.getId());

        assertEquals(userUpdateRequest.username(), result.username());
        assertEquals(userUpdateRequest.latitude(), result.latitude());
        assertEquals(userUpdateRequest.longitude(), result.longitude());
    }

    @Test
    void shouldSaveUser() {
        //given
        UserSaveRequest userSaveRequest = new UserSaveRequest("Batuhan", "Ozudogru", "12345678901", "batuhanozudogru", LocalDate.of(1990, 1, 1), BigDecimal.valueOf(0.50), BigDecimal.valueOf(0.40));

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setStatus(Status.ACTIVE);
        savedUser.setFirstName("Batuhan");
        savedUser.setLastName("Ozudogru");
        savedUser.setTurkishRepublicIdNumber("12345678901");
        savedUser.setUsername("batuhanozudogru");
        savedUser.setBirthDate(LocalDate.of(1990, 1, 1));
        savedUser.setLatitude(BigDecimal.valueOf(0.50));
        savedUser.setLongitude(BigDecimal.valueOf(0.40));

        //when
        Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(savedUser);

        UserResponse result = userControllerContractImpl.saveUser(userSaveRequest);

        //then

        assertEquals(savedUser.getFirstName(), result.firstName());
        assertEquals(savedUser.getLastName(), result.lastName());
    }

    @Test
    void shouldGetUserByTurkishRepublicIdNo() {
        //given
        String turkishRepublicIdNo = "12345678901";
        User user = new User();
        user.setStatus(Status.ACTIVE);
        user.setFirstName("Batuhan");
        user.setLastName("Ozudogru");
        user.setTurkishRepublicIdNumber(turkishRepublicIdNo);

        //when
        Mockito.when(userService.findByTurkishRepublicIdNo(Mockito.anyString())).thenReturn(user);

        UserResponse result = userControllerContractImpl.getUserByTurkishRepublicIdNo(turkishRepublicIdNo);

        //then
        assertEquals(user.getFirstName(), result.firstName());
        assertEquals(user.getLastName(), result.lastName());
    }

    @Test
    void shouldGetUserByUsername() {
        //given
        String username = "batuhanozudogru";
        User user = new User();
        user.setStatus(Status.ACTIVE);
        user.setFirstName("Batuhan");
        user.setLastName("Ozudogru");
        user.setUsername(username);

        //when
        Mockito.when(userService.findByUsername(Mockito.anyString())).thenReturn(user);

        UserResponse result = userControllerContractImpl.getUserByUsername(username);

        //then
        assertEquals(user.getFirstName(), result.firstName());
        assertEquals(user.getLastName(), result.lastName());
    }

    @Test
    void shouldActivateUser() {
        //given
        Long id = 1L;

        //when
        userControllerContractImpl.activeUser(id);

        //then
        Mockito.verify(userService, Mockito.times(1)).activeUser(id);
    }

    @Test
    void shouldSoftDeleteUser() {
        //given
        Long id = 1L;

        //when
        userControllerContractImpl.softDeleteUser(id);

        //then
        Mockito.verify(userService, Mockito.times(1)).passiveUser(id);
    }


    @Test
    void shouldHardDeleteUser() {
        //given
        Long id = 1L;

        //when
        userControllerContractImpl.hardDeleteUser(id);

        //then
        Mockito.verify(userService, Mockito.times(1)).deleteById(id);
    }
}