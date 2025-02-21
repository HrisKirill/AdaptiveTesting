package com.khrystoforov.adaptivetesting.question.repository;

import com.khrystoforov.adaptivetesting.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("""
             SELECT q FROM Question q
                WHERE q.id = (
                    SELECT q1.id FROM Question q1
                    ORDER BY ABS(q1.difficulty - :theta) ASC
                    LIMIT 1
                )
            """)
    Optional<Question> findClosestQuestion(@Param("theta") BigDecimal theta);

    Optional<Question> findByIdAndTopicId(Long id, Long topicId);
}
