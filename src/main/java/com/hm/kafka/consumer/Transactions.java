package com.hm.kafka.consumer;

import com.hm.kafka.common.Foo2;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Transactions {
    private final Logger LOGGER = LoggerFactory.getLogger(Transactions.class);
   /* @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public BatchMessagingMessageConverter batchConverter() {
        return new BatchMessagingMessageConverter(converter());
    }*/

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(id = "fooGroup2", topics = "topic2")
    public void listen1(List<Foo2> foos) throws IOException {
        LOGGER.info("Received: " + foos);
        foos.forEach(f -> kafkaTemplate.send("topic3", f.getFoo().toUpperCase()));
        LOGGER.info("Messages sent, hit Enter to commit tx");
        //System.in.read();
    }

    @KafkaListener(id = "fooGroup3", topics = "topic3")
    public void listen2(List<String> in) {
        LOGGER.info("Received: " + in);
        //LATCH.countDown();
    }

    /*@Bean
    public NewTopic topic2() {
        return TopicBuilder.name("topic2").partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic topic3() {
        return TopicBuilder.name("topic3").partitions(1).replicas(1).build();
    }*/

}
