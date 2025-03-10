package com.khrystoforov.adaptivetesting.topic.repository;

import com.khrystoforov.adaptivetesting.topic.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByName(String name);
}
