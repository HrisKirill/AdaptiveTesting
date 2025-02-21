package com.khrystoforov.adaptivetesting.user.repository;

import com.khrystoforov.adaptivetesting.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
