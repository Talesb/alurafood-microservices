package br.com.alurafood.orders.amqp;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdersAMQPConfiguration {

	
	@Bean
	public RabbitAdmin createRabbitAdmin(ConnectionFactory connection) {
		return new RabbitAdmin(connection);
	}

	@Bean
	public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin) {
		return event -> rabbitAdmin.initialize();
	}

	
	
	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate reabbitTemplate(ConnectionFactory connectionFactory,
			Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
		return rabbitTemplate;
	}

	@Bean
	public Queue finishedPaymentsQueue() {
		return QueueBuilder.nonDurable("payments.finished-orders").build();
	}
	
	@Bean
	public FanoutExchange fanoutExchange() {
		return ExchangeBuilder.fanoutExchange("payments.ex").build();
	}
	
	
	@Bean
	public Binding bindingPaymentOrdersExchange(FanoutExchange fanoutExchange) {
		return  BindingBuilder.bind(finishedPaymentsQueue()).to(fanoutExchange);
	}
}
