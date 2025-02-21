package com.khrystoforov.adaptivetesting.topic.service;

import com.khrystoforov.adaptivetesting.topic.model.Topic;

public interface TopicService {
    Topic createIfNotExistsByName(String name);

    Topic getByName(String topicName);
}
