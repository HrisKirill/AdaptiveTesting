package com.khrystoforov.adaptivetesting.user.repository;

import com.khrystoforov.adaptivetesting.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsUserByEmail(String email);
}
