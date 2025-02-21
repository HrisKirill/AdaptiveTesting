package com.khrystoforov.adaptivetesting.topic.service.impl;

import com.khrystoforov.adaptivetesting.exception.EntityNotFoundException;
import com.khrystoforov.adaptivetesting.topic.model.Topic;
import com.khrystoforov.adaptivetesting.topic.repository.TopicRepository;
import com.khrystoforov.adaptivetesting.topic.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository repository;

    @Override
    public Topic createIfNotExistsByName(String name) {
        return repository.findByName(name)
                .orElseGet(() -> repository.save(new Topic(name)));
    }

    @Override
    public Topic getByName(String topicName) {
        return repository.findByName(topicName)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found with name " + topicName));
    }
}
