package com.khrystoforov.adaptivetesting.answerOption.repository;

import com.khrystoforov.adaptivetesting.answerOption.model.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
}
