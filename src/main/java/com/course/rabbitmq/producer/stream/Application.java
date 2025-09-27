package com.course.rabbitmq.producer.stream;

import com.course.rabbitmq.producer.stream.entity.Invoice;
import com.course.rabbitmq.producer.stream.producer.StreamInvoiceProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private StreamInvoiceProducer producer;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			var invoice = new Invoice("INV-" + i, Invoice.Status.CREATED,
					ThreadLocalRandom.current().nextInt(100, 1000));
			if (i % 2 == 0) {
				producer.sendInvoiceUsingRabbitTemplate(invoice);
			} else {
				producer.sendInvoiceUsingRabbitStreamTemplate(invoice);
			}
			TimeUnit.MILLISECONDS.sleep(100);
		}
		System.out.println("All invoices sent");
	}
}
