package com.mycompany.kafkaconsumer.kafka;

import com.mycompany.kafkaconsumer.domain.News;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@KafkaListener(groupId = "${app.kafka.group-id}", offsetReset = OffsetReset.EARLIEST)
public class NewsListener {

    @Topic("${app.kafka.input.topics}")
    public void receive(@KafkaKey String key, News newsMessage, long offset, int partition, String topic, long timestamp) {
        log.info("Received message\n---\nTOPIC: {}; PARTITION: {}; OFFSET: {}; TIMESTAMP: {};\nKEY: {}\nPAYLOAD: {}\n---",
                topic, partition, offset, timestamp, key, newsMessage);
    }
}
