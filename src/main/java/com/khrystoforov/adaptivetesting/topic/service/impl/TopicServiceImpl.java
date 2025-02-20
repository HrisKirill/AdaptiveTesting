package com.khrystoforov.adaptivetesting.topic.service.impl;

import com.khrystoforov.adaptivetesting.topic.model.Topic;
import com.khrystoforov.adaptivetesting.topic.repository.TopicRepository;
import com.khrystoforov.adaptivetesting.topic.service.TopicService;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository repository;

    public TopicServiceImpl(TopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public Topic createIfNotExistsByName(String name) {
        return repository.findByName(name)
                .orElseGet(() -> repository.save(new Topic(name)));
    }
}
