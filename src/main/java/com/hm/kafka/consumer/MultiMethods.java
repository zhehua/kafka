package com.hm.kafka.consumer;

import com.hm.kafka.common.Bar2;
import com.hm.kafka.common.Foo2;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "multiGroup", topics = { "foos","bars"})
public class MultiMethods {
    @KafkaHandler
    public void foo(Foo2 foo){
        System.out.println(foo.toString());
    }
    @KafkaHandler
    public void bar(Bar2 bar){
        System.out.println(bar.toString());
    }
    @KafkaHandler(isDefault = true)
    public void unknow(Object o){
        System.out.println("Received unknown: " + o);
    }
}
