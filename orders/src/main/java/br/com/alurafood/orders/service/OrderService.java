package br.com.alurafood.orders.service;

import br.com.alurafood.orders.dto.OrderRequestDto;
import br.com.alurafood.orders.dto.StatusDto;
import br.com.alurafood.orders.model.OrderRequest;
import br.com.alurafood.orders.model.Status;
import br.com.alurafood.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private final ModelMapper modelMapper;


    public List<OrderRequestDto> getAll() {
        return repository.findAll().stream()
                .map(p -> modelMapper.map(p, OrderRequestDto.class))
                .collect(Collectors.toList());
    }

    public OrderRequestDto getById(Long id) {
        OrderRequest order = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(order, OrderRequestDto.class);
    }

    public OrderRequestDto createOrder(OrderRequestDto dto) {
        OrderRequest order = modelMapper.map(dto, OrderRequest.class);

        order.setDateTimeOrder(LocalDateTime.now());
        order.setStatus(Status.REALIZADO);
        order.getOderItems().forEach(item -> item.setOrderrequest(order));
        OrderRequest salvo = repository.save(order);

        return modelMapper.map(order, OrderRequestDto.class);
    }

    public OrderRequestDto updateStatus(Long id, StatusDto dto) {

        OrderRequest order = repository.getByIdWithItems(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(dto.getStatus());
        repository.updateStatus(dto.getStatus(), order);
        return modelMapper.map(order, OrderRequestDto.class);
    }

    public void aproveOrderPayment(Long id) {

        OrderRequest order = repository.getByIdWithItems(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(Status.PAGO);
        repository.updateStatus(Status.PAGO, order);
    }
}
