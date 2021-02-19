package com.hm.kafka.controller;

import com.hm.kafka.common.Bar1;
import com.hm.kafka.common.Foo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
    @GetMapping("/kafka/send/{msg}")
    public void sendMsg(@PathVariable String msg){
        kafkaTemplate.send("topic",msg);

    }
    @GetMapping("/kafka/foo/{msg}")
    public void sendFoo(@PathVariable String msg){
        kafkaTemplate.send("foos",new Foo1(msg));
    }

    @GetMapping("/kafka/bar/{msg}")
    public void sendBar(@PathVariable String msg){
        kafkaTemplate.send("bars",new Bar1(msg));
    }

    @GetMapping("/kafka/unknow/{msg}")
    public void sendunknow(@PathVariable String msg){
        kafkaTemplate.send("bars",msg);
    }

    @GetMapping(path = "/kafka/foos/{what}")
    public void sendFoos(@PathVariable String what) {
       /* kafkaTemplate.executeInTransaction(kafkaTemplate -> {
            StringUtils.commaDelimitedListToSet(what).stream()
                    .map(s -> new Foo1(s))
                    .forEach(foo -> kafkaTemplate.send("topic2", foo));
            return null;
        });*/
    }
}
