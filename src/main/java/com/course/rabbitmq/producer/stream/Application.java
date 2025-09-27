package com.course.rabbitmq.producer.stream;

import com.course.rabbitmq.producer.stream.producer.SuperStreamNumberProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private SuperStreamNumberProducer producer;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		producer.sendNumbersUsingRabbitTemplate(0, 10);
		producer.sendNumbersUsingRabbitStreamTemplate(10, 20);
	}
}
