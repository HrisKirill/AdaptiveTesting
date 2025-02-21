package com.khrystoforov.adaptivetesting.question.model;

import com.khrystoforov.adaptivetesting.topic.model.Topic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "questions")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"topic"})
@ToString(exclude = {"topic"})
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
    private BigDecimal discrimination;
    @Column(nullable = false)
    private BigDecimal guessing;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Question(String text, BigDecimal difficulty, BigDecimal guessing, BigDecimal discrimination, Topic topic) {
        this.text = text;
        this.difficulty = difficulty;
        this.guessing = guessing;
        this.topic = topic;
        this.discrimination = discrimination;
    }
}
