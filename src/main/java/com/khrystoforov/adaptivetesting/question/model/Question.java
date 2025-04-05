package com.khrystoforov.adaptivetesting.question.model;

import com.khrystoforov.adaptivetesting.answerOption.model.AnswerOption;
import com.khrystoforov.adaptivetesting.topic.model.Topic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"topic", "options"})
@ToString(exclude = {"topic", "options"})
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private BigDecimal difficulty;
    @Column(nullable = false)
    private BigDecimal guessing;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<AnswerOption> options = new HashSet<>();

    public void addOption(AnswerOption option) {
        options.add(option);
        option.setQuestion(this);
    }

    public void removeComment(AnswerOption option) {
        options.remove(option);
        option.setQuestion(null);
    }

    public Question(String text,
                    BigDecimal difficulty,
                    BigDecimal guessing,
                    Topic topic) {
        this.text = text;
        this.difficulty = difficulty;
        this.guessing = guessing;
        this.topic = topic;
    }
}
