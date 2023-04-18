package br.com.alurafood.orders.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentMessageListener {

	
	@RabbitListener(queues="payment.finished")
	public void receiveMessage(Message message) {
		System.out.println("Message received"+message.toString());
	}
	
	
	
	
}
