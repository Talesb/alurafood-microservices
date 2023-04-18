package br.com.alurafood.orders.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.alurafood.orders.dto.PaymentDTO;

@Component
public class PaymentMessageListener {

	
	@RabbitListener(queues="payment.finished")
	public void receiveMessage(PaymentDTO paymenDto) {
	
		System.out.println("Message received:  code:"+paymenDto.getCode()+" Status: "+paymenDto.getStatus()+"id "+paymenDto.getId() );
	
	}
	
	
	
	
}
