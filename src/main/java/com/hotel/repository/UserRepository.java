package com.hotel.repository;


import com.hotel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<Object> findByLogin(String username);

    boolean existsByLogin(String login);
}
