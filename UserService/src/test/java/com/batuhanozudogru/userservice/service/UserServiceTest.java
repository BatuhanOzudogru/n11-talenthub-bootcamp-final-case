package com.batuhanozudogru.userservice.service;

import com.batuhanozudogru.userservice.dao.UserRepository;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.general.enums.Status;
import com.batuhanozudogru.userservice.general.exception.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MernisService mernisService;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;


    @Test
    void shouldNotSaveUserWhenTurkishRepublicIdNoIsNull() {
        //given
        User user = new User();
        user.setFirstName("Batuhan");
        user.setLastName("Özüdoğru");
        user.setUsername("batuhanozudogru");
        user.setBirthDate(LocalDate.of(1997, 1, 1));
        user.setLatitude(BigDecimal.valueOf(0.50));
        user.setLongitude(BigDecimal.valueOf(0.40));

        //when
        //User savedUser = userService.save(user);

        //then
        assertThrows(TurkishRepublicIdNoCanNotBeVerifiedException.class, () -> userService.save(user));
    }

    @Test
    void shouldUpdateUserDetails() {
        // given
        User user = new User();
        user.setId(1L);
        user.setFirstName("UpdatedFirstName");
        user.setLastName("UpdatedLastName");
        user.setTurkishRepublicIdNumber("12345678901");
        user.setUsername("updatedusername");
        user.setBirthDate(LocalDate.of(1997, 1, 1));
        user.setLatitude(BigDecimal.valueOf(0.50));
        user.setLongitude(BigDecimal.valueOf(0.40));
        user.setStatus(Status.ACTIVE);

        //when
        Mockito.when(mernisService.verifyUser(user.getTurkishRepublicIdNumber(),user.getFirstName(),user.getLastName(), String.valueOf(user.getBirthDate().getYear()))).thenReturn(true);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User updatedUser = userService.save(user);
        //then
        assertEquals("UpdatedFirstName", updatedUser.getFirstName());
        assertEquals("UpdatedLastName", updatedUser.getLastName());
        assertEquals("updatedusername", updatedUser.getUsername());
    }

    @Test
    void shouldThrowExceptionWhenFieldLengthExceeds() {
        //given
        User user = new User();
        user.setFirstName("ThisIsAVeryLongFirstNameThatExceedsTheMaxLengthAllowed");
        user.setLastName("ThisIsAVeryLongLastNameThatExceedsTheMaxLengthAllowed");
        user.setTurkishRepublicIdNumber("12345678901234567890");
        user.setUsername("ThisIsAVeryLongUsernameThatExceedsTheMaxLengthAllowed");
        user.setBirthDate(LocalDate.of(1997, 1, 1));
        user.setLatitude(BigDecimal.valueOf(0.50));
        user.setLongitude(BigDecimal.valueOf(0.40));

        //then
        assertThrows(FieldLengthExceededException.class, () -> userService.save(user));
    }

    @Test
    void shouldNotSaveUserWhenFirstNameIsNull() {
        //given
        User user = new User();
        user.setLastName("Özüdoğru");
        user.setTurkishRepublicIdNumber("12345678901");
        user.setUsername("batuhanozudogru");
        user.setBirthDate(LocalDate.of(1997, 1, 1));
        user.setLatitude(BigDecimal.valueOf(0.50));
        user.setLongitude(BigDecimal.valueOf(0.40));

        //when
        //User savedUser = userService.save(user);

        //then

        assertThrows(TurkishRepublicIdNoCanNotBeVerifiedException.class, () -> userService.save(user));

    }

    @Test
    void shouldActivateUser() {
        // given
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setStatus(Status.PASSIVE);
        //when
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);


        userService.activeUser(userId);

        // then
        assertEquals(Status.ACTIVE, user.getStatus());
    }

    @Test
    void shouldNotSaveUserWhenTurkishRepublicIdIsTaken(){
        //given
        User user = new User();
        user.setFirstName("Batuhan");
        user.setLastName("Özüdoğru");
        user.setTurkishRepublicIdNumber("12345678901");
        user.setUsername("batuhanozudogru");
        user.setBirthDate(LocalDate.of(1997, 1, 1));
        user.setLatitude(BigDecimal.valueOf(0.50));
        user.setLongitude(BigDecimal.valueOf(0.40));

        //when
        Mockito.when(userRepository.findByTurkishRepublicIdNumber(user.getTurkishRepublicIdNumber())).thenReturn(Optional.of(user));

        //then
        assertThrows(TurkishRepublicIdNoAlreadyExistException.class, () -> userService.save(user));
    }

    @Test
    void shouldNotSaveUserWhenTurkishRepublicIdNoIsWrong() {
        //given
        User user = new User();
        user.setFirstName("Batuhan");
        user.setLastName("Özüdoğru");
        user.setTurkishRepublicIdNumber("123456789");
        user.setUsername("batuhanozudogru");
        user.setBirthDate(LocalDate.of(1997, 1, 1));
        user.setLatitude(BigDecimal.valueOf(0.50));
        user.setLongitude(BigDecimal.valueOf(0.40));


        //when


       // User savedUser = userService.save(user);

        //then

        assertThrows(TurkishRepublicIdNoCanNotBeVerifiedException.class, () -> userService.save(user));

    }

    @Test
    void shouldNotSaveUserWhenUsernameWasTaken() {
        //given
        User user = new User();
        user.setFirstName("Batuhan");
        user.setLastName("Özüdoğru");
        user.setTurkishRepublicIdNumber("12345678901");
        user.setUsername("batuhanozudogru");
        user.setBirthDate(LocalDate.of(1997, 1, 1));
        user.setLatitude(BigDecimal.valueOf(0.50));
        user.setLongitude(BigDecimal.valueOf(0.40));

        user.setStatus(Status.ACTIVE);

        //when
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        //then
        assertThrows(UsernameTakenException.class, () -> userService.save(user));
    }

    @Test
    void shouldFindAllActiveUsers() {
        //given
        User user1 = new User();
        user1.setStatus(Status.ACTIVE);
        User user2 = new User();
        user2.setStatus(Status.ACTIVE);
        List<User> users = List.of(user1, user2);

        //when
        Mockito.when(userRepository.findByStatus(Status.ACTIVE)).thenReturn(users);

        List<User> result = userService.findAllActiveUsers();

        //then
        assertEquals(users.size(), result.size());
    }

    @Test
    void shouldFindUserById() {
        //given
        Long id = 1L;
        User user = new User();
        user.setId(id);

        //when
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User result = userService.findById(id);

        //then
        assertEquals(id, result.getId());
    }

    @Test
    void shouldNotUpdateUserWhenUsernameWasTaken(){

        //given
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setUsername("batuhanozudogru");

        User user2 = new User();
        user2.setUsername("batuhanozudogru");

        //when
        Mockito.when(userRepository.findByUsername(user2.getUsername())).thenReturn(Optional.of(user2));

        //then
        assertThrows(UsernameTakenException.class, () -> userService.save(user2));
    }

    @Test
    void shouldThrowExceptionWhenFindingUserByIdNotFound() {
        //given
        Long id = 1L;

        //when
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        //then
        assertThrows(UserNotFoundException.class, () -> userService.findById(id));
    }

    @Test
    void shouldFindUserByTurkishRepublicIdNo() {
        //given
        String turkishRepublicIdNumber = "12345678901";
        User user = new User();
        user.setTurkishRepublicIdNumber(turkishRepublicIdNumber);

        //when
        when(userRepository.findByTurkishRepublicIdNumber(turkishRepublicIdNumber)).thenReturn(Optional.of(user));

        User result = userService.findByTurkishRepublicIdNo(turkishRepublicIdNumber);

        //then
        assertEquals(turkishRepublicIdNumber, result.getTurkishRepublicIdNumber());
    }

    @Test
    void shouldThrowExceptionWhenFindingUserByTurkishRepublicIdNoNotFound() {
        //given
        String turkishRepublicIdNumber = "12345678901";

        //when
        Mockito.when(userRepository.findByTurkishRepublicIdNumber(turkishRepublicIdNumber)).thenReturn(Optional.empty());

        //then
        assertThrows(UserNotFoundException.class, () -> userService.findByTurkishRepublicIdNo(turkishRepublicIdNumber));
    }

    @Test
    void shouldFindUserByUsername() {
        //given
        String username = "batuhanozudogru";
        User user = new User();
        user.setUsername(username);

        //when
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        User result = userService.findByUsername(username);

        //then
        assertEquals(username, result.getUsername());
    }

    @Test
    void shouldThrowExceptionWhenFindingUserByUsernameNotFound() {
        //given
        String username = "batuhanozudogru";

        //when
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        //then
        assertThrows(UserNotFoundException.class, () -> userService.findByUsername(username));
    }
}