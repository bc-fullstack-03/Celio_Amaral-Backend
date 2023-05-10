package com.sysmap.restApi.service.event;

//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;

//@Service
public class EventService implements IEventService {

//    @Autowired
//    private KafkaTemplate<String, String> _kafka;
//    @Value("${topic.name.producer}")
//    private String topic;

//    public void send(String event) {
//        _kafka.send(topic, event);
//    }

    // Dados de forma geral para consumir
//    @KafkaListener(topics = "${topic.name}", groupId = "ms-restApi")
//    public void consumer(ConsumerRecord<String, String> event) {
//        System.out.println("NOSSO EVENTO: " + event.value());
//    }

}
