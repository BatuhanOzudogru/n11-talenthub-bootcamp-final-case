package com.batuhanozudogru.userservice.service;

import com.batuhanozudogru.userservice.dao.UserRepository;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.general.enums.Status;
import com.batuhanozudogru.userservice.general.exception.*;
import com.batuhanozudogru.userservice.general.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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

        return userRepository.save(user);
    }

    public User update(User user) {

        LocalDateTime now = LocalDateTime.now();



        user.setUpdatedAt(now);

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

        if (!mernisService.verifyUser(user.getTurkishRepublicIdNumber(), user.getFirstName(), user.getLastName(), String.valueOf(user.getBirthDate().getYear()))) {
            throw new TurkishRepublicIdNoCanNotBeVerifiedException();
        }
    }

    private void validateStringLength(String fieldName, String value, int maxLength) {
        if (value != null && value.length() > maxLength) {
            throw new FieldLengthExceededException(Message.FIELD_LENGTH_EXCEEDED(fieldName, maxLength));
        }
    }

    public List<User> findAllActiveUsers(){
        return userRepository.findByStatus(Status.ACTIVE);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(Message.USER_NOT_FOUND_BY_ID(id)));
    }

    public User findByTurkishRepublicIdNo(String turkishRepublicIdNumber) {

        return userRepository.findByTurkishRepublicIdNumber(turkishRepublicIdNumber).orElseThrow(
                () -> new UserNotFoundException(Message.USER_NOT_FOUND_BY_TURKISH_REPUBLIC_ID_NUMBER(turkishRepublicIdNumber)));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(Message.USER_NOT_FOUND_BY_USERNAME(username)));
    }

    public void activeUser(Long id) {

        User user = findById(id);

        user.setStatus(Status.ACTIVE);

        userRepository.save(user);
    }

    public void deleteById(Long id) {

        User user = findById(id);

        userRepository.deleteById(id);
    }

    public void passiveUser(Long id) {

        User user = findById(id);

        user.setStatus(Status.PASSIVE);

        userRepository.save(user);
    }

}
