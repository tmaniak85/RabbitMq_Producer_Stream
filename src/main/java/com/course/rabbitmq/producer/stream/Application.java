package com.course.rabbitmq.producer.stream;

import com.course.rabbitmq.producer.stream.entity.Invoice;
import com.course.rabbitmq.producer.stream.producer.SuperStreamInvoiceProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private SuperStreamInvoiceProducer producer;

	@Autowired
	private RabbitTemplate dummyRabbitTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dummyRabbitTemplate.convertAndSend("Test");

		for (int i = 0; i < 10; i++) {
			var invoiceAmount = ThreadLocalRandom.current().nextInt(100, 1000);
			var invoiceCreated = new Invoice("INV-" + i, Invoice.Status.CREATED, invoiceAmount);
			var invoiceApproved = new Invoice("INV-" + i, Invoice.Status.APPROVED, invoiceAmount);

			producer.sendInvoiceUsingRabbitStreamTemplate(invoiceCreated);
			producer.sendInvoiceUsingRabbitStreamTemplate(invoiceApproved);
		}

		System.out.println("All invoices sent");
	}
}
