package com.course.rabbitmq.producer.stream.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreamNumberProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendNumber(int start, int end) {
        for (int i = start; i < end; i++) {
            var str = "Number " + i;
            rabbitTemplate.convertAndSend("x.number", "", str);
        }

        System.out.printf("Sent %d to %d", start, (end - 1));
    }
}
