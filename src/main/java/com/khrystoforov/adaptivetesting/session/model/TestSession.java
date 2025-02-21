package com.khrystoforov.adaptivetesting.session.model;

import com.khrystoforov.adaptivetesting.topic.model.Topic;
import com.khrystoforov.adaptivetesting.user.User;
import com.khrystoforov.adaptivetesting.userAnswer.model.UserAnswer;
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
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "test_sessions")
@Data
@NoArgsConstructor
public class TestSession {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal score = BigDecimal.ZERO;
    @Column(nullable = false)
    private BigDecimal currentTheta = BigDecimal.ZERO;
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<UserAnswer> answers;

    public TestSession(User user, Topic topic) {
        this.user = user;
        this.topic = topic;
    }
}
