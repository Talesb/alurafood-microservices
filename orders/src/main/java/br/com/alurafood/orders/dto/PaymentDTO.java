package br.com.alurafood.orders.dto;

import java.math.BigDecimal;

import br.com.alurafood.orders.model.PaymentStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PaymentDTO {
	
	private Long id;
	private BigDecimal value;
	private String name;
	private String number;
	private String expiration;
	private String code;
	private PaymentStatus status;
	private Long orderId;
	private Long paymentMethodId;

	
}
