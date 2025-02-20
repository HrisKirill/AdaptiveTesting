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
    private Integer difficulty;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Question(String text, Integer difficulty, Topic topic) {
        this.text = text;
        this.difficulty = difficulty;
        this.topic = topic;
    }
}
