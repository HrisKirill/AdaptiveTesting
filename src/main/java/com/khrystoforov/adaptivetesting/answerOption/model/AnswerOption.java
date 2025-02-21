package com.khrystoforov.adaptivetesting.answerOption.model;

import com.khrystoforov.adaptivetesting.question.model.Question;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "answer_options")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"question"})
@ToString(exclude = {"question"})
@NoArgsConstructor
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private boolean isCorrect;
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;


    public AnswerOption(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public AnswerOption(String text, boolean isCorrect, Question question) {
        this.text = text;
        this.isCorrect = isCorrect;
        this.question = question;
    }
}
