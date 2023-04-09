package br.com.alurafood.orders.controller;

import br.com.alurafood.orders.dto.OrderRequestDto;
import br.com.alurafood.orders.dto.StatusDto;
import br.com.alurafood.orders.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

        @Autowired
        private OrderService service;

        @GetMapping()
        public List<OrderRequestDto> getAll() {
            return service.getAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<OrderRequestDto> getById(@PathVariable @NotNull Long id) {
            OrderRequestDto dto = service.getById(id);
            return  ResponseEntity.ok(dto);
        }

        @PostMapping()
        public ResponseEntity<OrderRequestDto> createOrder(@RequestBody @Valid OrderRequestDto dto, UriComponentsBuilder uriBuilder) {
            OrderRequestDto createdOrder = service.createOrder(dto);

            URI path = uriBuilder.path("/orders/{id}").buildAndExpand(createdOrder.getId()).toUri();

            return ResponseEntity.created(path).body(createdOrder);

        }

        @PutMapping("/{id}/status")
        public ResponseEntity<OrderRequestDto> updateStatus(@PathVariable Long id, @RequestBody StatusDto status){
           OrderRequestDto dto = service.updateStatus(id, status);

            return ResponseEntity.ok(dto);
        }


        @PutMapping("/{id}/paid")
        public ResponseEntity<Void> aproveOrderPayment(@PathVariable @NotNull Long id) {
            service.aproveOrderPayment(id);

            return ResponseEntity.ok().build();

        }
}
