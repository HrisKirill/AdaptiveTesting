package com.khrystoforov.adaptivetesting.question.repository;

import com.khrystoforov.adaptivetesting.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
