package com.khrystoforov.adaptivetesting.userAnswer.repository;

import com.khrystoforov.adaptivetesting.userAnswer.model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
}
