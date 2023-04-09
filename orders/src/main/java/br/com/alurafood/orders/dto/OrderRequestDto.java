package br.com.alurafood.orders.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.alurafood.orders.model.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private Long id;
    private LocalDateTime dateTimeOrder;
    private Status status;
    private List<OrderItemDto> oderItems = new ArrayList<>();



}
