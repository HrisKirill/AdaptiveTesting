package com.khrystoforov.adaptivetesting.topic.model;

import com.khrystoforov.adaptivetesting.question.model.Question;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "topics")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"questions"})
@ToString(exclude = {"questions"})
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Question> questions;

    public Topic(String name) {
        this.name = name;
    }
}
