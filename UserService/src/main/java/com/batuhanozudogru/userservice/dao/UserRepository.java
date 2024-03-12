package com.batuhanozudogru.userservice.dao;

import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.general.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByTurkishRepublicIdNumber(String turkishRepublicIdNumber);

    List<User> findByStatus(Status status);
}
