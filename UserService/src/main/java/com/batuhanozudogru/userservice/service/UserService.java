package com.batuhanozudogru.userservice.service;

import com.batuhanozudogru.userservice.dao.UserRepository;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.general.enums.Status;
import com.batuhanozudogru.userservice.general.exception.*;
import com.batuhanozudogru.userservice.general.message.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService  {

    private final UserRepository userRepository;
    private final MernisService mernisService;
    public User save(User user) {

        LocalDateTime now = LocalDateTime.now();

        if (user.getCreatedAt() == null) {

            validateUser(user);

            user.setCreatedAt(now);
        }

        user.setUpdatedAt(now);

        log.info("User saved successfully: {}", user.getFirstName()+" "+user.getLastName());

        return userRepository.save(user);
    }


    private void validateUser(User user) {

        validateStringLength("First Name", user.getFirstName(), 50);

        validateStringLength("Last Name", user.getLastName(), 50);

        validateStringLength("Turkish Republic ID Number", user.getTurkishRepublicIdNumber(), 11);

        validateStringLength("Username", user.getUsername(), 30);

        Optional<User> usernameFromDb = userRepository.findByUsername(user.getUsername());

        Optional<User> turkishRepublicIdFromDb = userRepository.findByTurkishRepublicIdNumber(user.getTurkishRepublicIdNumber());

        if (turkishRepublicIdFromDb.isPresent()) {
            throw new TurkishRepublicIdNoAlreadyExistException();
        }

        if (usernameFromDb.isPresent()) {
            throw new UsernameTakenException();
        }

        if (Boolean.FALSE.equals(mernisService.verifyUser(user.getTurkishRepublicIdNumber(), user.getFirstName(), user.getLastName(), String.valueOf(user.getBirthDate().getYear())))) {
            throw new TurkishRepublicIdNoCanNotBeVerifiedException();
        }

        log.info("User validated successfully: {}", user.getFirstName()+" "+user.getLastName());
    }

    private void validateStringLength(String fieldName, String value, int maxLength) {
        if (value != null && value.length() > maxLength) {
            throw new FieldLengthExceededException(Message.FIELD_LENGTH_EXCEEDED(fieldName, maxLength));
        }
    }

    public List<User> findAllActiveUsers(){
        return userRepository.findByStatus(Status.ACTIVE);
    }

    public List<User> findAllPassiveUsers(){
        return userRepository.findByStatus(Status.PASSIVE);
    }

    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> {
                    log.error("User not found with ID : {}", id);

                    return new UserNotFoundException(Message.USER_NOT_FOUND_BY_ID(id));
                });

        log.info("User found by ID : {}", id);

        return user;
    }

    public User findByTurkishRepublicIdNo(String turkishRepublicIdNumber) {

        return userRepository.findByTurkishRepublicIdNumber(turkishRepublicIdNumber).orElseThrow(
                () -> {
                    log.error("User not found with Turkish Republic ID : {}", turkishRepublicIdNumber);

                    return new UserNotFoundException(Message.USER_NOT_FOUND_BY_TURKISH_REPUBLIC_ID_NUMBER(turkishRepublicIdNumber));
                });
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> {
                    log.error("User not found with username : {}", username);

                    return new UserNotFoundException(Message.USER_NOT_FOUND_BY_USERNAME(username));
                });
    }

    public void activeUser(Long id) {

        User user = findById(id);

        user.setStatus(Status.ACTIVE);

        userRepository.save(user);

        log.info("User activated successfully : {}", id);
    }

    public void deleteById(Long id) {

        User user = findById(id);

        userRepository.deleteById(id);

        log.info("User deleted successfully : {}", id);
    }

    public void passiveUser(Long id) {

        User user = findById(id);

        user.setStatus(Status.PASSIVE);

        userRepository.save(user);

        log.info("User set to passive successfully : {}", id);
    }

}
