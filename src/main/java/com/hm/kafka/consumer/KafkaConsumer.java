package com.hm.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = {"topic_1"},groupId = "group_1")
    public void onMessage(ConsumerRecord<?,?> record){
        System.out.println(record.toString());

    }
}
