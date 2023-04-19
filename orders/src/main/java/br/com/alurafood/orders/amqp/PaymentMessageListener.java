package br.com.alurafood.orders.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.alurafood.orders.dto.PaymentDTO;

@Component
public class PaymentMessageListener {

	
	@RabbitListener(queues="payments.finished-orders")
	public void receiveMessage(PaymentDTO paymenDto) {
	
		System.out.println("Message received:  code:"+paymenDto.getCode()+" Status: "+paymenDto.getStatus()+"id "+paymenDto.getId() );
	
	}
	
	
	
	
}
