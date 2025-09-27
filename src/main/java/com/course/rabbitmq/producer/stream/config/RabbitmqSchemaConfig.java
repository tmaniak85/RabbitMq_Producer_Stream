package com.course.rabbitmq.producer.stream.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitmqSchemaConfig {

//    @Bean
    Declarables rabbitSchema() {
        var exchange = ExchangeBuilder.fanoutExchange("x.number").durable(true).build();
        var queue = QueueBuilder.durable("s.number").stream().build();
        var binding = BindingBuilder.bind(queue).to(exchange).with("").noargs();

        return new Declarables(exchange, queue, binding);
    }

}
