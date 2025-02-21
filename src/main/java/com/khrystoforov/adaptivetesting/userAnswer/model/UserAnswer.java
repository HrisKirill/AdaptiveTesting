package com.khrystoforov.adaptivetesting.userAnswer.model;

import com.khrystoforov.adaptivetesting.answerOption.model.AnswerOption;
import com.khrystoforov.adaptivetesting.question.model.Question;
import com.khrystoforov.adaptivetesting.session.model.TestSession;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_answers")
@Data
@NoArgsConstructor
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "session_id")
    private TestSession session;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @ManyToOne
    @JoinColumn(name = "answer_option_id")
    private AnswerOption selectedAnswer;


    public UserAnswer(TestSession session, Question question, AnswerOption selectedAnswer) {
        this.session = session;
        this.question = question;
        this.selectedAnswer = selectedAnswer;
    }
}